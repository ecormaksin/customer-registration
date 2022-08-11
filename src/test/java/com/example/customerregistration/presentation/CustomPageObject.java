package com.example.customerregistration.presentation;

import org.openqa.selenium.WebDriver;

public abstract class CustomPageObject {

    protected static final String URL_BASE = "http://localhost:9990";

    protected WebDriver driver;

    public CustomPageObject(WebDriver driver) {
        this.driver = driver;
    }
}
