package com.eventures.tests;

import com.eventures.base.BaseTest;
import com.eventures.base.Utils;
import com.eventures.pages.LoginPage;
import org.testng.Assert;
import org.testng.Reporter;
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
        loginPage.Open();
        loginPage.EnterUsername(username);
        loginPage.EnterPassword(password);
        loginPage.ClickLogin();

        Reporter.log("Test executed with Username: " + username + " and Password: " + password);

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/");
    }

    @Test(priority = 2)
    public void LoginWithInvalidCredentials() {
        loginPage.Open();
        loginPage.EnterUsername(password);
        loginPage.EnterPassword(Utils.generateString(5));
        loginPage.ClickLogin();

        Assert.assertEquals(loginPage.GetErrorMessage(), "Invalid login attempt.");
        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Login");
    }

    @Test(priority = 4)
    public void LoginWithEmptyPasswordField() {
        loginPage.Open();
        loginPage.EnterUsername(password);
        loginPage.ClickLogin();

        Assert.assertEquals(loginPage.GetErrorMessage(), "The Password field is required.");
        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Login");
    }

    @Test(priority = 3)
    public void RegisterLink() {
        loginPage.Open();
        loginPage.ClickRegister();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register?returnUrl=%2F");
    }

    @Test(priority = 3)
    public void HomeLink() {
        loginPage.Open();
        loginPage.ClickHome();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/");
    }
}
