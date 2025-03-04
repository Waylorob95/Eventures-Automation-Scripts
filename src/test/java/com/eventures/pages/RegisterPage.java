package com.eventures.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private WebDriver driver;
    private String url = "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register";

    // Locators
    private By usernameField = By.id("Input_Username");
    private By emailField = By.id("Input_Email");
    private By passwordField = By.id("Input_Password");
    private By confirmPasswordField = By.id("Input_ConfirmPassword");
    private By firstNameField = By.id("Input_FirstName");
    private By lastNameField = By.id("Input_LastName");
    private By registerButton = By.xpath("//button[@class=\"btn btn-primary\"]");
    private By homeLink = By.linkText("Home");
    private By errorMessage = By.xpath("//div[@class=\"text-danger validation-summary-errors\"]");


    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void Open() {
        driver.get(url);
    }

    public void EnterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void EnterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void EnterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void ConfirmPassword(String confirmPassword) {
        driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
    }

    public void EnterFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void EnterSecondName(String secondName) {
        driver.findElement(lastNameField).sendKeys(secondName);
    }

    public void ClickRegister() {
        driver.findElement(registerButton).click();
    }

    public void ClickHome() {
        driver.findElement(homeLink).click();
    }

    public String GetErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
