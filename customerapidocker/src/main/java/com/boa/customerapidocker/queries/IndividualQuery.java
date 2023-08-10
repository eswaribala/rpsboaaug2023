package com.boa.customerapidocker.queries;

import com.boa.customerapidocker.models.Individual;
import com.boa.customerapidocker.services.IndividualService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IndividualQuery implements GraphQLQueryResolver {

    @Autowired
    private IndividualService individualService;

    public List<Individual> findAllIndividuals(){
        return this.individualService.getAllIndividuals();
    }


    public Individual findIndividualByCustomerId(long customerId){
        return this.individualService.getIndividualById(customerId);
    }

    public List<Individual> findIndividualByFirstName(String firstName){
        return this.individualService.getIndividualByFirstName(firstName);
    }

    public List<Individual> findIndividualByContactNo(long contactNo){
        return this.individualService.getIndividualByContactNo(contactNo);
    }


}
