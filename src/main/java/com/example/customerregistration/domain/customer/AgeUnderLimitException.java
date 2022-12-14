package com.example.customerregistration.domain.customer;

public class AgeUnderLimitException extends IllegalStateException {

    public AgeUnderLimitException() {
        super(String.format("The age must be %d or over", Birthdate.AGE_UNDER_LIMIT));
    }
}
