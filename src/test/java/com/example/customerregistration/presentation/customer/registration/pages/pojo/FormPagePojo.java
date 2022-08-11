package com.example.customerregistration.presentation.customer.registration.pages.pojo;

import com.example.customerregistration.presentation.customer.registration.pages.base.FormPageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FormPagePojo extends FormPageBase {

    private static final String URL_RELATIVE_PATH = "/customer/pojo/register";

    public FormPagePojo(final WebDriver driver) {
        super(driver);
    }

    public static FormPagePojo to(WebDriver driver) {
        driver.get(URL_BASE + URL_RELATIVE_PATH);
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.urlContains(URL_RELATIVE_PATH));
        return PageFactory.initElements(driver, FormPagePojo.class);
    }

    protected String urlRelativePath() {
        return URL_RELATIVE_PATH;
    }
}
