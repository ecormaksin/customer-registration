package com.example.customerregistration.domain.customer;

import lombok.Getter;

import javax.validation.Valid;

@Getter
public class RegistrationRequest {

    @Valid CustomerNumber customerNumber;
    @Valid CustomerName customerName;
    @Valid Birthdate birthdate;
    @Valid RegistrationRequestDate registrationRequestDate;

    public RegistrationRequest(
                CustomerNumber customerNumber,
                CustomerName customerName,
                Birthdate birthdate,
                RegistrationRequestDate registrationRequestDate) throws AgeUnderLimitException {

        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.birthdate = birthdate;
        this.registrationRequestDate = registrationRequestDate;

        if (birthdate.isNotMatchedAgeLimitAt(registrationRequestDate)) {
            throw new AgeUnderLimitException();
        }
    }
}
