package com.boa.customerapi.mutations;

import com.boa.customerapi.models.Individual;
import com.boa.customerapi.services.IndividualService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;

@Component
public class IndividualMutation implements GraphQLMutationResolver {

    @Autowired
    private IndividualService individualService;

    public Individual addIndividual(IndividualInput individualInput){

    }
    public Individual updateIndividual(long customerId, String email){
        return this.individualService.updateIndividualByCustomerId(customerId,email);
    }
    public boolean deleteIndividual(long customerId){

        return this.individualService.deleteIndividualByCustomerId(customerId);
    }
}
