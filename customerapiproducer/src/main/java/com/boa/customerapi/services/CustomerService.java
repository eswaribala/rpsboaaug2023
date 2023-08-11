package com.boa.customerapi.services;

import com.boa.customerapi.facades.CustomerFacade;
import com.boa.customerapi.models.Customer;
import com.boa.customerapi.repositories.CustomerRepo;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
public class CustomerService {

    @Autowired
    private CustomerFacade customerFacade;
    @Autowired
    private CustomerRepo customerRepo;


    public boolean sendMessage(long customerId){

        Customer customer=this.customerRepo.findById(customerId).orElse(null);
        MessageChannel messageChannel=customerFacade.outChannel();

        if(customer!=null){
           return  messageChannel.send(MessageBuilder.withPayload(customer)
                    .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                    .build());
        }
        else
            return false;


    }

}
