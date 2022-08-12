package com.example.customerregistration.presentation.customer.registration.pages.base;

import com.example.customerregistration.presentation.CustomPageObject;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class FormPageBase extends CustomPageObject {

    protected WebElement customerName;

    protected WebElement birthdate;

    protected WebElement birthdateError;

    protected WebElement registerButton;

    public FormPageBase(final WebDriver driver) {
        super(driver);

        final String url = driver.getCurrentUrl();
        if (!url.endsWith(urlRelativePath())) {
            throw new IllegalStateException("Here is not the customer registration form page.: "  + url);
        }
    }

    protected abstract String urlRelativePath();

    public FormPageBase inputName(final String name) {
        this.customerName.clear();
        this.customerName.sendKeys(name);
        return this;
    }

    public FormPageBase inputBirthdate(final String birthdate) {
        this.birthdate.clear();
        this.birthdate.sendKeys(birthdate);
        return this;
    }

    public String birthdateErrorMessage() {
        try {
            return birthdateError.getText();
        } catch (NoSuchElementException e) {
            return "";
        }
    }

    public <T> T register(Class<T> resultPage, final String name, final String birthdate) {
        inputName(name);
        inputBirthdate(birthdate);
        return register(resultPage);
    }

    public <T> T register(Class<T> resultPage) {
        registerButton.click();
        return PageFactory.initElements(driver, resultPage);
    }
}
