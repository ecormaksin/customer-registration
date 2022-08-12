package com.example.customerregistration.domain.customer;

import mockit.Mock;
import mockit.MockUp;

import java.time.LocalDate;

public class FakeRegistrationRequestDate_20000101 extends MockUp<RegistrationRequestDate> {

    @Mock
    public static RegistrationRequestDate nowByStaticMethod() {
        return new RegistrationRequestDate(LocalDate.of(2000, 1, 1));
    }
}
