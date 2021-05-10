package com.broadview.zipkin;


import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import zipkin2.server.internal.EnableZipkinServer;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableZipkinServer
//@EnableDiscoveryClient
public class ZipkinApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ZipkinApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
//        SpringApplication.run(ZipkinApplication.class, args);
    }

}
