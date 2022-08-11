package com.example.customerregistration.domain.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.YEARS;

@AllArgsConstructor
@Getter
public class Birthdate {

    public static final long AGE_UNDER_LIMIT = 18;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate value;

    @Deprecated
    Birthdate() {}

    public static DateTimeFormatter formatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    public static Birthdate initialValue() {
        LocalDate initialValue = LocalDate.now().minusYears(AGE_UNDER_LIMIT);
        return new Birthdate(initialValue);
    }

    boolean isNotMatchedAgeLimitAt(final RegistrationRequestDate registrationRequestDate) throws AgeUnderLimitException {
        final long diff = YEARS.between(this.value, registrationRequestDate.value());
        return diff < AGE_UNDER_LIMIT;
    }
}
