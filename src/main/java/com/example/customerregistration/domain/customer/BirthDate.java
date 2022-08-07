package com.example.customerregistration.domain.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.YEARS;

@AllArgsConstructor
@Getter
public class BirthDate{

    static final long AGE_UNDER_LIMIT = 18;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate value;

    @Deprecated
    BirthDate() {}

    public static BirthDate initialValue() {
        LocalDate initialValue = LocalDate.now().minusYears(AGE_UNDER_LIMIT);
        return new BirthDate(initialValue);
    }

    boolean isNotMatchedAgeLimitAt(final RegistrationRequestDate registrationRequestDate) throws AgeUnderLimitException {
        final long diff = YEARS.between(this.value, registrationRequestDate.value());
        return diff < AGE_UNDER_LIMIT;
    }
}
