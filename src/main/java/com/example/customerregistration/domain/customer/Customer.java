package com.example.customerregistration.domain.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;

@AllArgsConstructor
@Getter
public class Customer {
    @Valid CustomerNumber customerNumber;
    @Valid CustomerName customerName;
    @Valid Birthdate birthdate;

    @Deprecated
    Customer() {}
}
