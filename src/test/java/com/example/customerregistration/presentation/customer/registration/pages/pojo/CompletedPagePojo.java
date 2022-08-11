package com.example.customerregistration.presentation.customer.registration.pages.pojo;

import com.example.customerregistration.presentation.customer.registration.pages.base.CompletedPageBase;
import com.example.customerregistration.presentation.customer.registration.pages.base.FormPageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CompletedPagePojo extends CompletedPageBase {

    private static final String URL_RELATIVE_PATH = "/customer/pojo/register/completed";

    public CompletedPagePojo(final WebDriver driver) {
        super(driver);
    }

    protected String urlRelativePath() {
        return URL_RELATIVE_PATH;
    }
}
