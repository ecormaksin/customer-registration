package com.example.customerregistration.presentation.customer.registration;

import com.example.customerregistration.domain.customer.*;
import com.example.customerregistration.presentation.customer.registration.pages.pojo.CompletedPagePojo;
import com.example.customerregistration.presentation.customer.registration.pages.pojo.FormPagePojo;
import com.example.customerregistration.usecase.scenario.CustomerRegistrationScenario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerPojoWithJMockitTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebDriver driver;

    @Autowired
    CustomerRegistrationScenario registrationScenario;

    @Test
    void test_WhenNameAndBirthdateIsInputAppropriately_ThenRegistrationSucceeds() throws Exception {

        new FakeCustomerNumber_1();
        new FakeRegistrationRequestDate_20000101();

        FormPagePojo formPage = FormPagePojo.to(driver);

        final String customerName = "John Doe";
        final String birthdate = "1982-01-01";

        CompletedPagePojo completedPage =
            formPage.register(CompletedPagePojo.class, customerName, birthdate);

        final CustomerNumber customerNumberMocked = CustomerNumberTestHelper.fromInt(1);
        assertEquals(customerNumberMocked.value(), completedPage.customerNumber());
        assertEquals(customerName, completedPage.customerName());
        assertEquals(birthdate, completedPage.birthdate());
    }

    @Test
    void test_WhenBirthdateIsUnderTheLimit_ThenValidationFails() {

        new FakeRegistrationRequestDate_20000101();

        FormPagePojo formPage = FormPagePojo.to(driver);

        FormPagePojo returnedFormPage =
            formPage.register(FormPagePojo.class, "Joe Bloggs", "1983-01-01");

        assertEquals("The age must be 18 or over", returnedFormPage.birthdateErrorMessage());
    }

    @AfterEach
    void destroy() {
        if (driver == null) return;
        driver.close();
    }
}
