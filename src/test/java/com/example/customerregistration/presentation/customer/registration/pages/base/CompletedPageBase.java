package com.example.customerregistration.presentation.customer.registration.pages.base;

import com.example.customerregistration.presentation.CustomPageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class CompletedPageBase extends CustomPageObject {

    protected WebElement customerNumber;

    protected WebElement customerName;

    protected WebElement birthdate;

    public CompletedPageBase(final WebDriver driver) {
        super(driver);

        final String url = driver.getCurrentUrl();
        if (!url.endsWith(urlRelativePath())) {
            throw new IllegalStateException("Here is not the customer registration completed page.: "  + url);
        }
    }

    protected abstract String urlRelativePath();

    public String customerNumber() {
        return this.customerNumber.getText();
    }

    public String customerName() {
        return this.customerName.getText();
    }

    public String birthdate() {
        return this.birthdate.getText();
    }
}
