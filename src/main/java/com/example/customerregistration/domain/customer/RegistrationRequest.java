package com.example.customerregistration.domain.customer;

import lombok.Getter;

import javax.validation.Valid;

@Getter
public class RegistrationRequest {

    @Valid Number number;
    @Valid Name name;
    @Valid BirthDate birthDate;
    @Valid RegistrationRequestDate registrationRequestDate;

    public RegistrationRequest(
                Number number,
                Name name,
                BirthDate birthDate,
                RegistrationRequestDate registrationRequestDate) throws AgeUnderLimitException {

        this.number = number;
        this.name = name;
        this.birthDate = birthDate;
        this.registrationRequestDate = registrationRequestDate;

        if (birthDate.isNotMatchedAgeLimitAt(registrationRequestDate)) {
            throw new AgeUnderLimitException();
        }
    }
}
