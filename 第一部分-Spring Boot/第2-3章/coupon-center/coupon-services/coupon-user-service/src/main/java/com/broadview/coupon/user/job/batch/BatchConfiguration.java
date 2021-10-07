package com.broadview.coupon.user.job.batch;

import com.broadview.coupon.user.entity.Coupon;
import com.broadview.coupon.user.job.Counter;
import com.broadview.coupon.user.job.quartz.QuartzConfiguration;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;


@Configuration
@EnableBatchProcessing
@Import({QuartzConfiguration.class})
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;

	private static final String QUERY_FIND_COUPONS = "SELECT * FROM coupon";

	private Resource outputResource = new FileSystemResource("output/outputData.csv");

	@Bean
	public ItemReader<Coupon> couponReader() {
		return new JdbcCursorItemReaderBuilder<Coupon>()
				.name("couponItemReader")
				.dataSource(dataSource)
				.sql(QUERY_FIND_COUPONS)
				.rowMapper(new BeanPropertyRowMapper<>(Coupon.class))
				.build();
	}



	@Bean
	public CouponCountProcessor couponProcessor() {
		return new CouponCountProcessor();
	}

	@Bean
	public FlatFileItemWriter<Counter> writer()
	{
		FlatFileItemWriter<Counter> writer = new FlatFileItemWriter<>();
		writer.setResource(outputResource);
		writer.setAppendAllowed(true);
		writer.setLineAggregator(new DelimitedLineAggregator<Counter>() {
			{
				setDelimiter(",");
				setFieldExtractor(new BeanWrapperFieldExtractor<Counter>() {
					{
						setNames(new String[] { "couponId", "count" });
					}
				});
			}
		});
		return writer;
	}

	// Configure job step
	@Bean
	public Job couponETLJob() {
		return jobBuilderFactory.get("coupon_etl_job").incrementer(new RunIdIncrementer())
				.flow(etlStep()).end().build();
	}

	@Bean
	public Step etlStep() {
		return stepBuilderFactory.get("Extract -> Transform -> Store").allowStartIfComplete(true)
				.<Coupon, Counter> chunk(10).reader(couponReader()).processor(couponProcessor())
				.writer(writer()).build();
	}

}
