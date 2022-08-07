package com.example.customerregistration.domain.customer;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record RegistrationRequestDate(@NotNull LocalDate value) {

    public static RegistrationRequestDate now() {
        return new RegistrationRequestDate(LocalDate.now());
    }
}
