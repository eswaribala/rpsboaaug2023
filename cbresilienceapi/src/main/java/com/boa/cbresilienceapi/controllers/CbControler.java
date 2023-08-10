package com.boa.cbresilienceapi.controllers;

import com.boa.cbresilienceapi.services.CBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CbControler {

    @Autowired
    private CBService cbService;

    @GetMapping("/individuals")
    public String getData(){
        return this.cbService.getIndividuals();
    }
   
}
