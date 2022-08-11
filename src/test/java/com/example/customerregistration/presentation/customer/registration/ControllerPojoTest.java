package com.example.customerregistration.presentation.customer.registration;

import com.example.customerregistration.domain.customer.BirthdateTestHelper;
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
public class ControllerPojoTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebDriver driver;

    @Autowired
    CustomerRegistrationScenario registrationScenario;

    @Test
    void test_WhenNameAndBirthdateIsInputAppropriately_ThenRegistrationSucceeds() throws Exception {

        FormPagePojo formPage = FormPagePojo.to(driver);

        final String customerName = "John Doe";
        final String birthdate = BirthdateTestHelper.valueOverTheAgeLimit();

        CompletedPagePojo completedPage =
            formPage.register(CompletedPagePojo.class, customerName, birthdate);

        assertEquals(customerName, completedPage.name());
        assertEquals(birthdate, completedPage.birthdate());
    }

    @Test
    void test_WhenBirthdateIsUnderTheLimit_ThenValidationFails() {

        FormPagePojo formPage = FormPagePojo.to(driver);

        final String birthdate = BirthdateTestHelper.valueUnderTheAgeLimit();

        FormPagePojo returnedFormPage =
            formPage.register(FormPagePojo.class, "Joe Bloggs", birthdate);

        assertEquals("The age must be 18 or over", returnedFormPage.birthdateErrorMessage());
    }

    @AfterEach
    void destroy() {
        if (driver == null) return;
        driver.close();
    }
}
