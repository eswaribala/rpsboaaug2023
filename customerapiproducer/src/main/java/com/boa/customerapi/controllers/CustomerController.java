package com.boa.customerapi.controllers;

import com.boa.customerapi.dtos.ResponseWrapper;
import com.boa.customerapi.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping({"/v1.0/{customerId}"})
    public ResponseEntity<ResponseWrapper> publishData(@PathVariable("customerId") long customerId){
       if( this.customerService.sendMessage(customerId)){
           return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper("Message Published..."));
       }
       else
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper("Message Not Published..."));
    }



}
