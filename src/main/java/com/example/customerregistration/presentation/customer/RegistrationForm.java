package com.example.customerregistration.presentation.customer;

import com.example.customerregistration.domain.customer.*;
import com.example.customerregistration.domain.customer.Number;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;

@AllArgsConstructor
@Getter
public class RegistrationForm{

    @Valid Name name;
    @Valid BirthDate birthDate;

    @Deprecated
    RegistrationForm() {}

    public static RegistrationForm newForm() {
        return new RegistrationForm(
            Name.blank(),
            BirthDate.initialValue()
        );
    }

    public RegistrationRequest toRegistrationRequest(Number number, RegistrationRequestDate registrationRequestDate) {
        return new RegistrationRequest(
            number,
            this.name,
            this.birthDate,
            registrationRequestDate
        );
    }
}
