package com.broadview.coupon.user.job.quartz;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Slf4j
@Data
public class QuartzJobLauncher extends QuartzJobBean {

	private String jobName;
	private JobLauncher jobLauncher;
	private JobLocator jobLocator;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			Job job = jobLocator.getJob(jobName);
			JobExecution jobExecution = jobLauncher.run(job, new JobParameters());
			log.info("{}_{} was completed successfully", job.getName(), jobExecution.getId());
		} catch (Exception e) {
			log.error("Encountered job execution exception!");
		}
	}

}
