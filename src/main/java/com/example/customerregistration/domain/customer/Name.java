package com.example.customerregistration.domain.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class Name{

    @NotBlank
    @Size(min = 5, max = 10)
    String value;

    @Deprecated
    Name() {}

    public static Name blank() {
        return new Name("");
    }
}
