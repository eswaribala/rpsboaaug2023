package com.boa.customerapidocker.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressInput {
    private long addressId;

    private String doorNo;

    private  String streetName;

    private String city;

    private long pincode;


    private CustomerInput customer;
}
