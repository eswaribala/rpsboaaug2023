package com.boa.customerapidocker.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWrapper<T> {

    private T payload;
    private String message;

    public ResponseWrapper(T payload){
        this.payload=payload;
    }

    public ResponseWrapper(String message){
        this.message=message;
    }
}
