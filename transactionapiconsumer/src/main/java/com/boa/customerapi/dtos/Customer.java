package com.boa.customerapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customers")
public class Customer {

    @BsonId
    private long customerId;

    private FullName name;

    private String email;

    private long contactNo;

    private String password;

    private Gender gender;

    private LocalDate dob;

}
