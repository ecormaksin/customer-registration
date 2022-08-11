package com.example.customerregistration.domain.customer;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CustomerNumberTest {

    @Test
    void test_WhenCallStaticMethod_ThenRandomUUIDReturned() {

        final CustomerNumber customerNumberMocked = CustomerNumberTestHelper.fromInt(1);

        final CustomerNumber customerNumber = CustomerNumber.newInstanceByStaticMethod();

        assertNotEquals(customerNumberMocked.value(), customerNumber.value());
    }

    @Test
    void test_WhenCallMockedStaticMethod_ThenSpecifiedValueReturned() {

        final CustomerNumber customerNumberMocked = CustomerNumberTestHelper.fromInt(1);

        try (MockedStatic<CustomerNumber> numberMockedStatic = Mockito.mockStatic(CustomerNumber.class);) {

            numberMockedStatic.when(CustomerNumber::newInstanceByStaticMethod).thenReturn(customerNumberMocked);

            final CustomerNumber customerNumber = CustomerNumber.newInstanceByStaticMethod();

            assertEquals(customerNumberMocked.value(), customerNumber.value());
        }
    }
}
