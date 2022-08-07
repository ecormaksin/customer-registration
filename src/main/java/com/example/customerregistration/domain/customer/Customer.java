package com.example.customerregistration.domain.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;

@AllArgsConstructor
@Getter
public class Customer {
    @Valid Number number;
    @Valid Name name;
    @Valid BirthDate birthDate;

    @Deprecated
    Customer() {}
}
