package com.example.customerregistration.domain.customer;

public class CustomerNumberTestHelper {
    private static final String PREFIX = "cstm";

    public static CustomerNumber fromInt(final int intValue) {
        final int formatLength = CustomerNumber.SIZE - PREFIX.length();
        final String value = PREFIX + String.format("%0" + formatLength + "d", intValue);

        return new CustomerNumber(value);
    }
}
