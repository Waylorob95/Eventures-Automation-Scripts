package com.eventures.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private String url = "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Login";

    // Locators
    private By usernameField = By.id("Input_Username");
    private By passwordField = By.id("Input_Password");
    private By loginButton = By.xpath("//button[@class=\"btn btn-primary\"]");
    private By registerLink = By.linkText("Register as a new user");
    private By homeLink = By.linkText("Home");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get(url);
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void clickRegister() {
        driver.findElement(registerLink).click();
    }

    public void clickHome() {
        driver.findElement(homeLink).click();
    }

/*    public String GetErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
*/
    public String getErrorMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"text-danger validation-summary-errors\"]"))).getText();
        } catch (TimeoutException e) {
            return "";
        }
    }


}
