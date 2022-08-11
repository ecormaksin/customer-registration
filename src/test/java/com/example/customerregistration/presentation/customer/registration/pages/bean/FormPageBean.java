package com.example.customerregistration.presentation.customer.registration.pages.bean;

import com.example.customerregistration.presentation.customer.registration.pages.base.FormPageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FormPageBean extends FormPageBase {

    private static final String URL_RELATIVE_PATH = "/customer/bean/register";

    public FormPageBean(final WebDriver driver) {
        super(driver);
    }

    public static FormPageBean to(WebDriver driver) {
        driver.get(URL_BASE + URL_RELATIVE_PATH);
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.urlContains(URL_RELATIVE_PATH));
        return PageFactory.initElements(driver, FormPageBean.class);
    }

    protected String urlRelativePath() {
        return URL_RELATIVE_PATH;
    }
}
