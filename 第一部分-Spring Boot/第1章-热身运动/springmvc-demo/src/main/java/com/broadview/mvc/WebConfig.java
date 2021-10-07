package com.broadview.mvc;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
/*
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.broadview.mvc.spring5"})
public class WebConfig implements WebMvcConfigurer {
    //Declare the static resources.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/") .setCachePeriod(31556926);
    }
    @Bean
    InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver(); resolver.setPrefix("/WEB-INF/views"); resolver.setSuffix(".jspx" ); resolver.setRequestContextAttribute("requestContext"); return resolver;
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("singers/list"); }

    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) { configurer.enable();
    }
}*/
