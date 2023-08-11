package com.boa.transactionapi.services;

import com.boa.transactionapi.dtos.Customer;
import com.boa.transactionapi.facades.CustomerFacade;
import com.boa.transactionapi.repositories.CustomerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @StreamListener(target = CustomerFacade.channelName)
    public void  consumeData(@Payload Customer customer){
        log.info("Message Received"+customer.getCustomerId());
        this.customerRepo.save(customer);

    }
}
