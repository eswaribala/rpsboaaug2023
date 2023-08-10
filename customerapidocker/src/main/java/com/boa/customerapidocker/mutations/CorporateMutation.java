package com.boa.customerapidocker.mutations;

import com.boa.customerapidocker.dtos.CorporateInput;
import com.boa.customerapidocker.models.FullName;
import com.boa.customerapidocker.models.Corporate;
import com.boa.customerapidocker.services.CorporateService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CorporateMutation implements GraphQLMutationResolver {

    @Autowired
    private CorporateService corporateService;

    public Corporate addCorporate(CorporateInput corporateInput){

        Corporate corporate=Corporate.builder()
                        .customerId(corporateInput.getCustomerId())
                        .contactNo(corporateInput.getContactNo())
                        .email(corporateInput.getEmail())
                        .password(corporateInput.getPassword())
                .companyType(corporateInput.getCompanyType())
                         .name(FullName.builder()
                                 .firstName(corporateInput.getName().getFirstName())
                                 .lastName(corporateInput.getName().getLastName())
                                 .middleName(corporateInput.getName().getMiddleName())
                                 .build())
                        .build();

        return this.corporateService.addCorporate(corporate);

    }
    public Corporate updateCorporate(long customerId, String email){
        return this.corporateService.updateCorporateByCustomerId(customerId,email);
    }
    public boolean deleteCorporate(long customerId){

        return this.corporateService.deleteCorporateByCustomerId(customerId);
    }
}
