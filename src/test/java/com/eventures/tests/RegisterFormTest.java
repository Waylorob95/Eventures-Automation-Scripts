package com.eventures.tests;

import com.eventures.base.BaseTest;
import com.eventures.base.TestDataGenerator;
import com.eventures.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class RegisterFormTest extends BaseTest {

    private RegisterPage registerPage;
    private String testUsername;
    private String testEmail;
    private String testPassword;
    private String testFirstName;
    private String testLastName;

    @BeforeClass
    public void RegisterPageInit() {
        registerPage = new RegisterPage(driver);
    }
    @Test(priority = 1)
    public void RegisterWithValidData() {
        registerPage.openPage();
        testUsername = TestDataGenerator.getRandomUsername();
        testEmail = TestDataGenerator.getRandomEmail();
        testPassword = TestDataGenerator.getRandomPassword();
        testFirstName = TestDataGenerator.getRandomFirstName();
        testLastName = TestDataGenerator.getRandomLastName();

        registerPage.enterUsername(testUsername);
        registerPage.enterEmail(testEmail);
        registerPage.enterPassword(testPassword);
        registerPage.confirmPassword(testPassword);
        registerPage.enterFirstName(testFirstName);
        registerPage.enterSecondName(testLastName);

        registerPage.clickRegister();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/");
        System.out.println("User was registered successfully!");
    }

    @Test(priority = 2)
    public void RegisterWithShortUsername() {
        registerPage.openPage();
        testUsername = TestDataGenerator.getRandomInvalidUsername();
        testEmail = TestDataGenerator.getRandomEmail();
        testPassword = TestDataGenerator.getRandomPassword();
        testFirstName = TestDataGenerator.getRandomFirstName();
        testLastName = TestDataGenerator.getRandomLastName();

        registerPage.enterUsername(testUsername);
        registerPage.enterEmail(testEmail);
        registerPage.enterPassword(testPassword);
        registerPage.confirmPassword(testPassword);
        registerPage.enterFirstName(testFirstName);
        registerPage.enterSecondName(testLastName);

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
        testUsername = TestDataGenerator.getRandomUsername() + "$";
        testEmail = TestDataGenerator.getRandomEmail();
        testPassword = TestDataGenerator.getRandomPassword();
        testFirstName = TestDataGenerator.getRandomFirstName();
        testLastName = TestDataGenerator.getRandomLastName();

        registerPage.enterUsername(testUsername);
        registerPage.enterEmail(testEmail);
        registerPage.enterPassword(testPassword);
        registerPage.confirmPassword(testPassword);
        registerPage.enterFirstName(testFirstName);
        registerPage.enterSecondName(testLastName);

        registerPage.clickRegister();

        if(driver.getCurrentUrl().equals("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register")){
            String errorMessage = registerPage.getErrorMessage();
            Assert.assertFalse(errorMessage.isEmpty(), "Expected error message, but none found!");
            Assert.assertEquals(errorMessage, "Username '" + testUsername + "' is invalid, can only contain letters or digits.", "Error message did not match!");
            System.out.println("Error message: " + errorMessage);
        } else {
            Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register", "User was registered successfully using username that contains special character and was redirected to the home page.");
        }
    }

    @Test(priority = 2)
    public void RegisterWithInvalidUsernameWithSpecialCharacters() {
        registerPage.openPage();
        testUsername = TestDataGenerator.getRandomUsername() + "@";
        testEmail = TestDataGenerator.getRandomEmail();
        testPassword = TestDataGenerator.getRandomPassword();
        testFirstName = TestDataGenerator.getRandomFirstName();
        testLastName = TestDataGenerator.getRandomLastName();


        registerPage.enterUsername(testUsername);
        registerPage.enterEmail(testEmail);
        registerPage.enterPassword(testPassword);
        registerPage.confirmPassword(testPassword);
        registerPage.enterFirstName(testFirstName);
        registerPage.enterSecondName(testLastName);

        registerPage.clickRegister();

        if(driver.getCurrentUrl().equals("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register")){
            String errorMessage = registerPage.getErrorMessage();
            Assert.assertFalse(errorMessage.isEmpty(), "Expected error message, but none found!");
            Assert.assertEquals(errorMessage, "Username '" + testUsername + "' is invalid, can only contain letters or digits.", "Error message did not match!");
            System.out.println("Error message: " + errorMessage);
        } else {
            Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register", "User was registered successfully using username that contains special character and was redirected to the home page.");
        }
    }

    @Test(priority = 2)
    public void RegisterWithTakenUsername() {
        registerPage.openPage();
        testEmail = TestDataGenerator.getRandomEmail();
        testPassword = TestDataGenerator.getRandomPassword();
        testFirstName = TestDataGenerator.getRandomFirstName();
        testLastName = TestDataGenerator.getRandomLastName();

        registerPage.enterUsername(username);
        registerPage.enterEmail(testEmail);
        registerPage.enterPassword(testPassword);
        registerPage.confirmPassword(testPassword);
        registerPage.enterFirstName(testFirstName);
        registerPage.enterSecondName(testLastName);

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
        testUsername = TestDataGenerator.getRandomUsername();
        testEmail = TestDataGenerator.getRandomEmail();
        testPassword = TestDataGenerator.getRandomInvalidPassword();
        testFirstName = TestDataGenerator.getRandomFirstName();
        testLastName = TestDataGenerator.getRandomLastName();

        registerPage.enterUsername(testUsername);
        registerPage.enterEmail(testEmail);
        registerPage.enterPassword(testPassword);
        registerPage.confirmPassword(testPassword);
        registerPage.enterFirstName(testFirstName);
        registerPage.enterSecondName(testLastName);

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
        testUsername = TestDataGenerator.getRandomUsername();
        testEmail = TestDataGenerator.getRandomEmail();
        testPassword = TestDataGenerator.getRandomPassword();
        testFirstName = TestDataGenerator.getRandomFirstName();
        testLastName = TestDataGenerator.getRandomLastName();

        registerPage.enterUsername(testUsername);
        registerPage.enterEmail(testEmail);
        registerPage.enterPassword(testPassword);
        registerPage.confirmPassword(testPassword + "123");
        registerPage.enterFirstName(testFirstName);
        registerPage.enterSecondName(testLastName);

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
