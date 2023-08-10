package com.boa.customerapidocker.mutations;

import com.boa.customerapidocker.dtos.IndividualInput;
import com.boa.customerapidocker.models.FullName;
import com.boa.customerapidocker.models.Individual;
import com.boa.customerapidocker.services.IndividualService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IndividualMutation implements GraphQLMutationResolver {

    @Autowired
    private IndividualService individualService;

    public Individual addIndividual(IndividualInput individualInput){

        Individual individual=Individual.builder()
                        .customerId(individualInput.getCustomerId())
                        .contactNo(individualInput.getContactNo())
                        .email(individualInput.getEmail())
                        .password(individualInput.getPassword())
                        .dob(individualInput.getDob())
                         .gender(individualInput.getGender())
                         .name(FullName.builder()
                                 .firstName(individualInput.getName().getFirstName())
                                 .lastName(individualInput.getName().getLastName())
                                 .middleName(individualInput.getName().getMiddleName())
                                 .build())
                        .build();

        return this.individualService.addIndividual(individual);

    }
    public Individual updateIndividual(long customerId, String email){
        return this.individualService.updateIndividualByCustomerId(customerId,email);
    }
    public boolean deleteIndividual(long customerId){

        return this.individualService.deleteIndividualByCustomerId(customerId);
    }
}
