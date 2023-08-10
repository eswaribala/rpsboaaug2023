package com.boa.customerapidocker.dtos;

import com.boa.customerapidocker.models.CompanyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CorporateInput {

    private long customerId;

    private FullNameInput name;

    private String email;

    private long contactNo;

    private String password;

    private CompanyType companyType;
}
