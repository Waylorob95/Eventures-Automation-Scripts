package com.eventures.tests;

import com.eventures.base.BaseTest;
import com.eventures.base.Utils;
import com.eventures.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class RegisterFormTest extends BaseTest {

    private RegisterPage registerPage;

    @BeforeClass
    public void RegisterPageInit() {
        registerPage = new RegisterPage(driver);
    }
    @Test(priority = 1)
    public void RegisterWithValidData() {
        registerPage.Open();

        registerPage.EnterUsername(Utils.generateString(5));
        registerPage.EnterEmail(Utils.generateString(5) + "@test.com");
        registerPage.EnterPassword("test123");
        registerPage.ConfirmPassword("test123");
        registerPage.EnterFirstName("Test");
        registerPage.EnterSecondName("Test");

        registerPage.ClickRegister();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/");
    }

    @Test(priority = 2)
    public void RegisterWithShortUsername() {
        registerPage.Open();

        registerPage.EnterUsername(Utils.generateString(5));
        registerPage.EnterEmail(Utils.generateString(4) + "@test.com");
        registerPage.EnterPassword("test123");
        registerPage.ConfirmPassword("test123");
        registerPage.EnterFirstName("Test");
        registerPage.EnterSecondName("Test");

        registerPage.ClickRegister();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register");
        Assert.assertEquals(registerPage.GetErrorMessage(), "The Username must be at least 5 characters long");
    }
    @Test(priority = 2)
    public void RegisterWithInvalidUsernameWithSpecialCharacter() {
        registerPage.Open();

        registerPage.EnterUsername(Utils.generateString(5) + "$");
        registerPage.EnterEmail(Utils.generateString(4) + "@test.com");
        registerPage.EnterPassword("test123");
        registerPage.ConfirmPassword("test123");
        registerPage.EnterFirstName("Test");
        registerPage.EnterSecondName("Test");

        registerPage.ClickRegister();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register");
        Assert.assertTrue(registerPage.GetErrorMessage() != null, "Username is invalid");
    }

    @Test(priority = 2)
    public void RegisterWithInvalidUsernameWithSpecialCharacters() {
        registerPage.Open();

        registerPage.EnterUsername(Utils.generateString(4) + "@");
        registerPage.EnterEmail(Utils.generateString(4) + "@test.com");
        registerPage.EnterPassword("test123");
        registerPage.ConfirmPassword("test123");
        registerPage.EnterFirstName("Test");
        registerPage.EnterSecondName("Test");

        registerPage.ClickRegister();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register");
        Assert.assertTrue(registerPage.GetErrorMessage() != null, "Username is invalid");
    }

    @Test(priority = 2)
    public void RegisterWithTakenUsername() {
        registerPage.Open();

        registerPage.EnterUsername(username);
        registerPage.EnterEmail(Utils.generateString(5) + "@test.com");
        registerPage.EnterPassword("test123");
        registerPage.ConfirmPassword("test123");
        registerPage.EnterFirstName("Test");
        registerPage.EnterSecondName("Test");

        registerPage.ClickRegister();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register");
        Assert.assertEquals(registerPage.GetErrorMessage(), "Username 'guest' is already taken.");
    }

    @Test(priority = 2)
    public void RegisterWithShortPassword() {
        registerPage.Open();

        registerPage.EnterUsername(Utils.generateString(5));
        registerPage.EnterEmail(Utils.generateString(4) + "@test.com");
        registerPage.EnterPassword(Utils.generateString(5));
        registerPage.ConfirmPassword(Utils.generateString(5));
        registerPage.EnterFirstName("Test");
        registerPage.EnterSecondName("Test");

        registerPage.ClickRegister();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register");
        Assert.assertEquals(registerPage.GetErrorMessage(), "The Password must be at least 6 and at max 20 characters long.");
    }

    @Test(priority = 1)
    public void RegisterWithWrongConfirmPassword() {
        registerPage.Open();

        registerPage.EnterUsername(Utils.generateString(5));
        registerPage.EnterEmail(Utils.generateString(4) + "@test.com");
        registerPage.EnterPassword(Utils.generateString(6));
        registerPage.ConfirmPassword(Utils.generateString(6) + "1");
        registerPage.EnterFirstName("Test");
        registerPage.EnterSecondName("Test");

        registerPage.ClickRegister();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register");
        Assert.assertEquals(registerPage.GetErrorMessage(), "The passwords does not match.");
    }

    @Test(priority = 3)
    public void HomeLink() {
        registerPage.Open();
        registerPage.ClickHome();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/");
    }

}
