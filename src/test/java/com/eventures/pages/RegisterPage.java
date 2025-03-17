package com.eventures.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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


    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get(url);
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void confirmPassword(String confirmPassword) {
        driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
    }

    public void enterFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void enterSecondName(String secondName) {
        driver.findElement(lastNameField).sendKeys(secondName);
    }

    public void clickRegister() {
        driver.findElement(registerButton).click();
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
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/main/div/div/form/div[1]"))).getText();
        } catch (TimeoutException e) {
             return "";
        }
}

}


