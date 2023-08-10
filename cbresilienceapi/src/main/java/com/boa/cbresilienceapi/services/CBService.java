package com.boa.cbresilienceapi.services;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@Slf4j
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
        log.info(" Making a request to " + gatewayUrl + " at :"
                + LocalDateTime.now());

        return restTemplate.getForObject(gatewayUrl, String.class);

    }


    public String fallBackGetIndividuals(Exception e){
        return restTemplate.getForObject(alternativeUrl, String.class);
    }

}
