package com.example.customerregistration.domain.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Component
public class RegistrationRequestDate {

    @NotNull LocalDate value;

    @Deprecated
    RegistrationRequestDate() {}

    public static RegistrationRequestDate nowByStaticMethod() {
        return new RegistrationRequestDate(LocalDate.now());
    }

    public RegistrationRequestDate nowByInstanceMethod() {
        return new RegistrationRequestDate(LocalDate.now());
    }
}
