package com.boa.transactionapi.facades;

import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(CustomerFacade.class)
public class StreamConfig {
}
