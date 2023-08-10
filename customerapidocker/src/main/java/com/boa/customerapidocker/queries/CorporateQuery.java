package com.boa.customerapidocker.queries;

import com.boa.customerapidocker.models.Corporate;
import com.boa.customerapidocker.services.CorporateService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CorporateQuery implements GraphQLQueryResolver {

    @Autowired
    private CorporateService corporateService;

    public List<Corporate> findAllCorporates(){
        return this.corporateService.getAllCorporates();
    }


    public Corporate findCorporateByCustomerId(long customerId){
        return this.corporateService.getCorporateById(customerId);
    }


}
