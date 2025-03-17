package com.eventures.tests;

import com.eventures.base.BaseTest;
import com.eventures.base.Utils;
import com.eventures.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginFormTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeClass
    public void LoginPageInit() {
        loginPage = new LoginPage(driver);
    }
    @Test(priority = 1)
    public void LoginWithValidCredentials() {
        loginPage.openPage();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/", "User cannot log in with valid credentials");
        System.out.println("Login successful!");
    }

    @Test(priority = 2)
    public void LoginWithInvalidCredentials() {
        loginPage.openPage();
        loginPage.enterUsername(username);
        loginPage.enterPassword(Utils.generateString(5));
        loginPage.clickLogin();

        if(driver.getCurrentUrl().equals("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Login")){
            String errorMessage = loginPage.getErrorMessage();
            Assert.assertFalse(errorMessage.isEmpty(), "Expected error message, but none found!");
            Assert.assertEquals(errorMessage, "Invalid login attempt.", "Error message did not match!");
            System.out.println("Error message: " + errorMessage);
        } else {
            Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/", "User logged in successfully with invalid credentials");
        }
    }

    @Test(priority = 2)
    public void LoginWithEmptyPasswordField() {
        loginPage.openPage();
        loginPage.enterUsername(password);
        loginPage.clickLogin();

        if(driver.getCurrentUrl().equals("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Login")){
            String errorMessage = loginPage.getErrorMessage();
            Assert.assertFalse(errorMessage.isEmpty(), "Expected error message, but none found!");
            Assert.assertEquals(errorMessage, "The Password field is required.", "Error message did not match!");
            System.out.println("Error message: " + errorMessage);
        } else {
            Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/", "User logged in successfully with empty password credential");
        }
    }

    @Test(priority = 3)
    public void RegisterLink() {
        loginPage.openPage();
        loginPage.clickRegister();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register?returnUrl=%2F", "User is not redirected to the register page.");
        System.out.println("Register link clicked! User redirected to the registration page.");
    }

    @Test(priority = 4)
    public void HomeLink() {
        loginPage.openPage();
        loginPage.clickHome();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/");
        System.out.println("Home link clicked! User redirected to the home page.");
    }
}
