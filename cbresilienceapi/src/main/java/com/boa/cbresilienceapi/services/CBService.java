package com.boa.cbresilienceapi.services;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CBService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${serviceUrl}")

    private String gatewayUrl;
    @Value("${alternativeServiceUrl}")

    private String alternativeUrl;



    @CircuitBreaker(name = "gatewayCircuitBreaker", fallbackMethod = "fallBackGetIndividuals")
    @Retry(name = "gatewayRetry")

    public String getIndividuals(){

        return restTemplate.getForObject(gatewayUrl, String.class);

    }


    public String fallBackGetIndividuals(Exception e){
        return restTemplate.getForObject(alternativeUrl, String.class);
    }

}
