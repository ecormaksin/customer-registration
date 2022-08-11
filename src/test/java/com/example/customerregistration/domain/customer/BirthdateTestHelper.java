package com.example.customerregistration.domain.customer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BirthdateTestHelper {

    private static String value(final long minusYears) {
        final LocalDate localDate = LocalDate.now().minusYears(minusYears);
        final DateTimeFormatter dateTimeFormatter = Birthdate.formatter();
        final String birthdate = localDate.format(dateTimeFormatter);

        return birthdate;
    }

    public static String valueOverTheAgeLimit() {
        return value(Birthdate.AGE_UNDER_LIMIT);
    }

    public static String valueUnderTheAgeLimit() {
        return value(Birthdate.AGE_UNDER_LIMIT - 1);
    }
}
