package com.example.customerregistration.domain.customer;

import mockit.Mock;
import mockit.MockUp;

public class FakeCustomerNumber_1 extends MockUp<CustomerNumber> {

    @Mock
    public static CustomerNumber newInstanceByStaticMethod() {
        return CustomerNumberTestHelper.fromInt(1);
    }
}
