package com.eventures.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    private String url = "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Login";

    // Locators
    private By usernameField = By.id("Input_Username");
    private By passwordField = By.id("Input_Password");
    private By loginButton = By.xpath("//button[@class=\"btn btn-primary\"]");
    private By registerLink = By.linkText("Register as a new user");
    private By homeLink = By.linkText("Home");
    private By errorMessage = By.xpath("//div[@class=\"text-danger validation-summary-errors\"]");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void Open() {
        driver.get(url);
    }

    public void EnterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void EnterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void ClickLogin() {
        driver.findElement(loginButton).click();
    }

    public void ClickRegister() {
        driver.findElement(registerLink).click();
    }

    public void ClickHome() {
        driver.findElement(homeLink).click();
    }

    public String GetErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
