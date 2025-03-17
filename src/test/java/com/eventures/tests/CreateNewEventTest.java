package com.eventures.tests;

import com.eventures.base.BaseTest;
import com.eventures.base.Utils;
import com.eventures.pages.CreateEventPage;
import com.eventures.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class CreateNewEventTest extends BaseTest {

    private LoginPage loginPage;
    private CreateEventPage createEventPage;

    @BeforeClass
    public void loginToApp() {
        loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        createEventPage = new CreateEventPage(driver);
    }


    @Test(priority = 1)
    public void TestingWithValidData() {
        createEventPage.openPage();
        createEventPage.clearData();

        createEventPage.enterName(Utils.generateString(5));
        createEventPage.enterPlace(Utils.generateString(7));
        createEventPage.enterStartDate("05/05/2025");
        createEventPage.enterEndDate("05/05/2026");
        createEventPage.enterPrice("10");
        createEventPage.enterTotalTickets("1000");
        createEventPage.clickCreate();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/All", "The event was not created");
        System.out.println("The event was created");
    }
    @Test(priority = 1)
    public void TestingTheNameFieldShort() {
        createEventPage.openPage();
        createEventPage.clearData();

        createEventPage.enterName(Utils.generateString(2));
        createEventPage.enterPlace(Utils.generateString(7));
        createEventPage.enterStartDate("05/05/2025");
        createEventPage.enterEndDate("05/05/2026");
        createEventPage.enterPrice("10");
        createEventPage.enterTotalTickets("1000");
        createEventPage.clickCreate();

        if(driver.getCurrentUrl().equals("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create")){
            String errorMessage = createEventPage.getErrorMessageName();
            Assert.assertFalse(errorMessage.isEmpty(), "Expected error message, but none found!");
            Assert.assertEquals(errorMessage, "Name should be at least 3 characters long.", "Error message did not match!");
            System.out.println("Error message: " + errorMessage);
        } else {
            Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create", "Event was created with short name field.");
        }
    }

    @Test(priority = 1)
    public void TestingTheNameFieldEmpty() {
        createEventPage.openPage();
        createEventPage.clearData();

        createEventPage.enterPlace(Utils.generateString(7));
        createEventPage.enterStartDate("05/05/2025");
        createEventPage.enterEndDate("05/05/2026");
        createEventPage.enterPrice("10");
        createEventPage.enterTotalTickets("1000");
        createEventPage.clickCreate();

        if(driver.getCurrentUrl().equals("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create")){
            String errorMessage = createEventPage.getErrorMessageName();
            Assert.assertFalse(errorMessage.isEmpty(), "Expected error message, but none found!");
            Assert.assertEquals(errorMessage, "The Name field is required.", "Error message did not match!");
            System.out.println("Error message: " + errorMessage);
        } else {
            Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create", "Event was created with empty name field.");
        }
    }

    @Test(priority = 2)
    public void TestingThePlaceFieldShort() {
        createEventPage.openPage();
        createEventPage.clearData();

        createEventPage.enterName(Utils.generateString(5));
        createEventPage.enterPlace(Utils.generateString(2));
        createEventPage.enterStartDate("05/05/2025");
        createEventPage.enterEndDate("05/05/2026");
        createEventPage.enterPrice("10");
        createEventPage.enterTotalTickets("1000");
        createEventPage.clickCreate();

        if(driver.getCurrentUrl().equals("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create")){
            String errorMessage = createEventPage.getErrorMessagePlace();
            Assert.assertFalse(errorMessage.isEmpty(), "Expected error message, but none found!");
            Assert.assertEquals(errorMessage, "Place should be at least 3 characters long.", "Error message did not match!");
            System.out.println("Error message: " + errorMessage);
        } else {
            Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create", "Event was created with empty name field.");
        }
    }

    @Test(priority = 1)
    public void TestingThePlaceFieldEmpty() {
        createEventPage.openPage();
        createEventPage.clearData();

        createEventPage.enterName(Utils.generateString(5));
        createEventPage.enterStartDate("05/05/2025");
        createEventPage.enterEndDate("05/05/2026");
        createEventPage.enterPrice("10");
        createEventPage.enterTotalTickets("1000");
        createEventPage.clickCreate();

        if(driver.getCurrentUrl().equals("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create")){
            String errorMessage = createEventPage.getErrorMessagePlace();
            Assert.assertFalse(errorMessage.isEmpty(), "Expected error message, but none found!");
            Assert.assertEquals(errorMessage, "The Place field is required.", "Error message did not match!");
            System.out.println("Error message: " + errorMessage);
        } else {
            Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create", "Event was created with empty name field.");
        }
    }

    @Test(priority = 1)
    public void TestingTheTotalTickets0() {
        createEventPage.openPage();
        createEventPage.clearData();

        createEventPage.enterName(Utils.generateString(5));
        createEventPage.enterPlace(Utils.generateString(7));
        createEventPage.enterStartDate("05/05/2025");
        createEventPage.enterEndDate("05/05/2026");
        createEventPage.enterPrice("10");
        createEventPage.enterTotalTickets("0");
        createEventPage.clickCreate();

        if(driver.getCurrentUrl().equals("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create")){
            String errorMessage = createEventPage.getErrorMessagePlace();
            Assert.assertFalse(errorMessage.isEmpty(), "Expected error message, but none found!");
            Assert.assertEquals(errorMessage, "Total Tickets must be a positive number and less than 1000.", "Error message did not match!");
            System.out.println("Error message: " + errorMessage);
        } else {
            Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create", "Event was created with 0 tickets");
        }
    }

    @Test(priority = 1)
    public void TestingThePriceTickets1100() {
        createEventPage.openPage();
        createEventPage.clearData();

        createEventPage.enterName(Utils.generateString(5));
        createEventPage.enterPlace(Utils.generateString(7));
        createEventPage.enterStartDate("05/05/2025");
        createEventPage.enterEndDate("05/05/2026");
        createEventPage.enterPrice("1100");
        createEventPage.enterTotalTickets("1000");
        createEventPage.clickCreate();

        if(driver.getCurrentUrl().equals("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create")){
            String errorMessage = createEventPage.getErrorMessageTotalTickets();
            Assert.assertFalse(errorMessage.isEmpty(), "Expected error message, but none found!");
            Assert.assertEquals(errorMessage, "Price Per Ticket must be a positive number and less than 1000.", "Error message did not match!");
            System.out.println("Error message: " + errorMessage);
        } else {
            Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create", "Event was created with very high ticket price");
        }
    }

    @Test(priority = 4)
    public void BackToList() {
        createEventPage.openPage();
        createEventPage.backToList();


        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/All", "Back to list unsuccessful");
        System.out.println("Back to list successful");
    }
}
