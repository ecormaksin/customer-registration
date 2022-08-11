package com.example.customerregistration.presentation.customer.registration;

import com.example.customerregistration.domain.customer.*;
import com.example.customerregistration.domain.customer.CustomerNumber;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;

@AllArgsConstructor
@Getter
public class Form {

    String actionPath;

    @Valid CustomerName customerName;
    @Valid Birthdate birthdate;

    @Deprecated
    Form() {}

    public static Form newForm(String actionPath) {
        return new Form(
            actionPath,
            CustomerName.blank(),
            Birthdate.initialValue()
        );
    }

    public RegistrationRequest toRegistrationRequest(CustomerNumber customerNumber, RegistrationRequestDate registrationRequestDate) {
        return new RegistrationRequest(
            customerNumber,
            this.customerName,
            this.birthdate,
            registrationRequestDate
        );
    }
}
