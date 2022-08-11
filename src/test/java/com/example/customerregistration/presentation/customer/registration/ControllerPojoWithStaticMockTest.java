package com.example.customerregistration.presentation.customer.registration;

import com.example.customerregistration.domain.customer.BirthdateTestHelper;
import com.example.customerregistration.domain.customer.CustomerNumber;
import com.example.customerregistration.domain.customer.CustomerNumberTestHelper;
import com.example.customerregistration.domain.customer.RegistrationRequestDate;
import com.example.customerregistration.presentation.customer.registration.pages.pojo.CompletedPagePojo;
import com.example.customerregistration.presentation.customer.registration.pages.pojo.FormPagePojo;
import com.example.customerregistration.usecase.scenario.CustomerRegistrationScenario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerPojoWithStaticMockTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebDriver driver;

    @Autowired
    CustomerRegistrationScenario registrationScenario;

    @Test
    void test_WhenNameAndBirthdateIsInputAppropriately_ThenRegistrationSucceeds() throws Exception {

        final CustomerNumber customerNumberMocked = CustomerNumberTestHelper.fromInt(1);
        final String customerNumberValue = customerNumberMocked.value();
        final RegistrationRequestDate registrationRequestDateMocked = new RegistrationRequestDate(LocalDate.of(2040, 8, 11));

        try (MockedStatic<CustomerNumber> numberMockedStatic = Mockito.mockStatic(CustomerNumber.class);
             MockedStatic<RegistrationRequestDate> requestDateMockedStatic = Mockito.mockStatic(RegistrationRequestDate.class);) {

            numberMockedStatic.when(CustomerNumber::newInstanceByStaticMethod).thenReturn(customerNumberMocked);
            requestDateMockedStatic.when(RegistrationRequestDate::nowByStaticMethod).thenReturn(registrationRequestDateMocked);

            FormPagePojo formPage = FormPagePojo.to(driver);

            final String customerName = "John Doe";
            final String birthdate = "2022-08-11";

            CompletedPagePojo completedPage =
                formPage.register(CompletedPagePojo.class, customerName, birthdate);

            assertEquals(customerNumberValue, completedPage.number());
            assertEquals(customerName, completedPage.name());
            assertEquals(birthdate, completedPage.birthdate());
        }
    }

    @Test
    void test_WhenBirthdateIsUnderTheLimit_ThenValidationFails() {

        final RegistrationRequestDate registrationRequestDateMocked = new RegistrationRequestDate(LocalDate.of(2021, 8, 11));

        try(MockedStatic<RegistrationRequestDate> requestDateMockedStatic = Mockito.mockStatic(RegistrationRequestDate.class);) {

            requestDateMockedStatic.when(RegistrationRequestDate::nowByStaticMethod).thenReturn(registrationRequestDateMocked);

            FormPagePojo formPage = FormPagePojo.to(driver);

            final String birthdate = BirthdateTestHelper.valueUnderTheAgeLimit();

            FormPagePojo returnedFormPage =
                formPage.register(FormPagePojo.class, "Joe Bloggs", "2004-08-11");

            assertEquals("The age must be 18 or over", returnedFormPage.birthdateErrorMessage());
        }
    }

    @AfterEach
    void destroy() {
        if (driver == null) return;
        driver.close();
    }
}
