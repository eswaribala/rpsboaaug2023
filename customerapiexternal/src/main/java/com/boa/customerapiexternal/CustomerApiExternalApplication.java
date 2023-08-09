package com.boa.customerapiexternal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CustomerApiExternalApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApiExternalApplication.class, args);
    }

}
