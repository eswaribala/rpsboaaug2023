package com.boa.customerapidocker.queries;

import com.boa.customerapidocker.models.Address;
import com.boa.customerapidocker.services.AddressService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressQuery implements GraphQLQueryResolver {
    @Autowired
    private AddressService addressService;

    public List<Address> findAllAddresses(){
        return this.addressService.getAllAddresss();
    }


    public List<Address> findAddressByCustomerId(long customerId){
        return this.addressService.getAddressByCustomerId(customerId);
    }


}
