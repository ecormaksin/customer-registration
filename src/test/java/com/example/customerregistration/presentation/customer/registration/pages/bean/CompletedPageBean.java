package com.example.customerregistration.presentation.customer.registration.pages.bean;

import com.example.customerregistration.presentation.customer.registration.pages.base.CompletedPageBase;
import org.openqa.selenium.WebDriver;

public class CompletedPageBean extends CompletedPageBase {

    private static final String URL_RELATIVE_PATH = "/customer/bean/register/completed";

    public CompletedPageBean(final WebDriver driver) {
        super(driver);
    }

    protected String urlRelativePath() {
        return URL_RELATIVE_PATH;
    }
}
