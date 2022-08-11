package com.example.customerregistration.presentation.customer.registration.pages.base;

import com.example.customerregistration.presentation.CustomPageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class CompletedPageBase extends CustomPageObject {

    @FindBy(id = "customerNumber")
    protected WebElement number;

    @FindBy(id = "customerName")
    protected WebElement name;

    protected WebElement birthdate;

    public CompletedPageBase(final WebDriver driver) {
        super(driver);

        final String url = driver.getCurrentUrl();
        if (!url.endsWith(urlRelativePath())) {
            throw new IllegalStateException("Here is not the customer registration completed page.: "  + url);
        }
    }

    protected abstract String urlRelativePath();

    public String number() {
        return this.number.getText();
    }

    public String name() {
        return this.name.getText();
    }

    public String birthdate() {
        return this.birthdate.getText();
    }
}
