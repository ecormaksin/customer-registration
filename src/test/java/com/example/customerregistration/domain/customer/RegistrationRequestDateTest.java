package com.example.customerregistration.domain.customer;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationRequestDateTest {

    @Test
    void test_WhenCallStaticMethod_ThenSystemDateReturned() {

        final RegistrationRequestDate registrationRequestDate = RegistrationRequestDate.nowByStaticMethod();

        assertEquals(LocalDate.now(), registrationRequestDate.value());
    }

    @Test
    void test_WhenCallMockedStaticMethod_ThenSpecifiedDateReturned() {
        final RegistrationRequestDate registrationRequestDateMocked = new RegistrationRequestDate(LocalDate.of(2000, 12, 31));

        try(MockedStatic<RegistrationRequestDate> requestDateMockedStatic = Mockito.mockStatic(RegistrationRequestDate.class);) {

            requestDateMockedStatic.when(RegistrationRequestDate::nowByStaticMethod).thenReturn(registrationRequestDateMocked);

            final RegistrationRequestDate registrationRequestDate = RegistrationRequestDate.nowByStaticMethod();

            assertEquals(LocalDate.of(2000, 12, 31), registrationRequestDate.value());
        }
    }
}
