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
        registerPage.openPage();

        registerPage.enterUsername(Utils.generateString(5));
        registerPage.enterEmail(Utils.generateString(5) + "@test.com");
        registerPage.enterPassword("test123");
        registerPage.confirmPassword("test123");
        registerPage.enterFirstName("Test");
        registerPage.enterSecondName("Test");

        registerPage.clickRegister();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/");
        System.out.println("User was registered successfully!");
    }

    @Test(priority = 2)
    public void RegisterWithShortUsername() {
        registerPage.openPage();

        registerPage.enterUsername(Utils.generateString(4));
        registerPage.enterEmail(Utils.generateString(4) + "@test.com");
        registerPage.enterPassword("test123");
        registerPage.confirmPassword("test123");
        registerPage.enterFirstName("Test");
        registerPage.enterSecondName("Test");

        registerPage.clickRegister();

        if(driver.getCurrentUrl().equals("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register")){
            String errorMessage = registerPage.getErrorMessage();
            Assert.assertFalse(errorMessage.isEmpty(), "Expected error message, but none found!");
            Assert.assertEquals(errorMessage, "The Username must be at least 5 characters long.", "Error message did not match!");
            System.out.println("Error message: " + errorMessage);
        } else {
            Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register", "User was registered successfully using short username and was redirected to the home page.");
        }
    }
    @Test(priority = 2)
    public void RegisterWithInvalidUsernameWithSpecialCharacter() {
        registerPage.openPage();
        username = Utils.generateString(5) + "$";

        registerPage.enterUsername(username);
        registerPage.enterEmail(Utils.generateString(4) + "@test.com");
        registerPage.enterPassword("test123");
        registerPage.confirmPassword("test123");
        registerPage.enterFirstName("Test");
        registerPage.enterSecondName("Test");

        registerPage.clickRegister();

        if(driver.getCurrentUrl().equals("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register")){
            String errorMessage = registerPage.getErrorMessage();
            Assert.assertFalse(errorMessage.isEmpty(), "Expected error message, but none found!");
            Assert.assertEquals(errorMessage, "Username '" + username + "' is invalid, can only contain letters or digits.", "Error message did not match!");
            System.out.println("Error message: " + errorMessage);
        } else {
            Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register", "User was registered successfully using username that contains special character and was redirected to the home page.");
        }
    }

    @Test(priority = 2)
    public void RegisterWithInvalidUsernameWithSpecialCharacters() {
        registerPage.openPage();
        username = Utils.generateString(5) + "@";


        registerPage.enterUsername(username);
        registerPage.enterEmail(Utils.generateString(4) + "@test.com");
        registerPage.enterPassword("test123");
        registerPage.confirmPassword("test123");
        registerPage.enterFirstName("Test");
        registerPage.enterSecondName("Test");

        registerPage.clickRegister();

        if(driver.getCurrentUrl().equals("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register")){
            String errorMessage = registerPage.getErrorMessage();
            Assert.assertFalse(errorMessage.isEmpty(), "Expected error message, but none found!");
            Assert.assertEquals(errorMessage, "Username '" + username + "' is invalid, can only contain letters or digits.", "Error message did not match!");
            System.out.println("Error message: " + errorMessage);
        } else {
            Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register", "User was registered successfully using username that contains special character and was redirected to the home page.");
        }
    }

    @Test(priority = 2)
    public void RegisterWithTakenUsername() {
        registerPage.openPage();
        username = "guest";

        registerPage.enterUsername(username);
        registerPage.enterEmail(Utils.generateString(5) + "@test.com");
        registerPage.enterPassword("test123");
        registerPage.confirmPassword("test123");
        registerPage.enterFirstName("Test");
        registerPage.enterSecondName("Test");

        registerPage.clickRegister();

        if(driver.getCurrentUrl().equals("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register")){
            String errorMessage = registerPage.getErrorMessage();
            Assert.assertFalse(errorMessage.isEmpty(), "Expected error message, but none found!");
            Assert.assertEquals(errorMessage, "Username '" + username + "' is already taken.", "Error message did not match!");
            System.out.println("Error message: " + errorMessage);
        } else {
            Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register", "User was registered successfully with already registered username");
        }
    }

    @Test(priority = 2)
    public void RegisterWithShortPassword() {
        registerPage.openPage();
        password = (Utils.generateString(5));

        registerPage.enterUsername(Utils.generateString(5));
        registerPage.enterEmail(Utils.generateString(4) + "@test.com");
        registerPage.enterPassword(password);
        registerPage.confirmPassword(password);
        registerPage.enterFirstName("Test");
        registerPage.enterSecondName("Test");

        registerPage.clickRegister();

        if(driver.getCurrentUrl().equals("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register")){
            String errorMessage = registerPage.getErrorMessage();
            Assert.assertFalse(errorMessage.isEmpty(), "Expected error message, but none found!");
            Assert.assertEquals(errorMessage, "The Password must be at least 6 and at max 20 characters long.", "Error message did not match!");
            System.out.println("Error message: " + errorMessage);
        } else {
            Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register", "User was registered successfully using short password");
        }
    }

    @Test(priority = 1)
    public void RegisterWithWrongConfirmPassword() {
        registerPage.openPage();
        password = (Utils.generateString(6));

        registerPage.enterUsername(Utils.generateString(5));
        registerPage.enterEmail(Utils.generateString(4) + "@test.com");
        registerPage.enterPassword(password);
        registerPage.confirmPassword(password + "123");
        registerPage.enterFirstName("Test");
        registerPage.enterSecondName("Test");

        registerPage.clickRegister();

        if(driver.getCurrentUrl().equals("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register")){
            String errorMessage = registerPage.getErrorMessage();
            Assert.assertFalse(errorMessage.isEmpty(), "Expected error message, but none found!");
            Assert.assertEquals(errorMessage, "The passwords does not match.", "Error message did not match!");
        } else {
            Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register", "User was registered successfully without confirming the password.");
        }
    }

    @Test(priority = 3)
    public void HomeLink() {
        registerPage.openPage();
        registerPage.clickHome();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/", "User is not redirected to the home page.");
        System.out.println("User redirected to the home page.");
    }

}
