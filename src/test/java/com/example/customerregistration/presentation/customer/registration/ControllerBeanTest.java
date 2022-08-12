package com.example.customerregistration.presentation.customer.registration;

import com.example.customerregistration.domain.customer.CustomerNumber;
import com.example.customerregistration.domain.customer.CustomerNumberTestHelper;
import com.example.customerregistration.domain.customer.RegistrationRequestDate;
import com.example.customerregistration.presentation.customer.registration.pages.bean.CompletedPageBean;
import com.example.customerregistration.presentation.customer.registration.pages.bean.FormPageBean;
import com.example.customerregistration.usecase.scenario.CustomerRegistrationScenario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerBeanTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebDriver driver;

    @MockBean
    CustomerNumber customerNumber;

    @MockBean
    RegistrationRequestDate registrationRequestDate;

    @Autowired
    CustomerRegistrationScenario registrationScenario;

    @Test
    void test_WhenNameAndBirthdateIsInputAppropriately_ThenRegistrationSucceeds() throws Exception {

        final CustomerNumber customerNumberMocked = CustomerNumberTestHelper.fromInt(1);
        final String customerNumberValue = customerNumberMocked.value();
        doReturn(customerNumberMocked).when(customerNumber)
            .newInstanceByInstanceMethod();

        final RegistrationRequestDate registrationRequestDateMocked = new RegistrationRequestDate(LocalDate.of(2000, 1, 1));
        doReturn(registrationRequestDateMocked).when(registrationRequestDate)
            .nowByInstanceMethod();

        FormPageBean formPage = FormPageBean.to(driver);

        final String customerName = "John Doe";
        final String birthdate = "1982-01-01";

        CompletedPageBean completedPage =
            formPage.register(CompletedPageBean.class, customerName, birthdate);

        assertEquals(customerNumberValue, completedPage.customerNumber());
        assertEquals(customerName, completedPage.customerName());
        assertEquals(birthdate, completedPage.birthdate());
    }

    @Test
    void test_WhenBirthdateIsUnderTheLimit_ThenValidationFails() {

        final RegistrationRequestDate registrationRequestDateMock = new RegistrationRequestDate(LocalDate.of(2000, 1, 1));
        doReturn(registrationRequestDateMock).when(registrationRequestDate)
            .nowByInstanceMethod();

        FormPageBean formPage = FormPageBean.to(driver);

        FormPageBean returnedFormPage =
            formPage.register(FormPageBean.class, "Joe Bloggs", "1983-01-01");

        assertEquals("The age must be 18 or over", returnedFormPage.birthdateErrorMessage());
    }

    @AfterEach
    void destroy() {
        if (driver == null) return;
        driver.close();
    }
}
