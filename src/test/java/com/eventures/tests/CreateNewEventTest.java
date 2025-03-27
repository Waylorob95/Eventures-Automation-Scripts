package com.eventures.tests;

import com.eventures.base.BaseTest;
import com.eventures.base.TestDataGenerator;
import com.eventures.base.Utils;
import com.eventures.pages.CreateEventPage;
import com.eventures.pages.LoginPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class CreateNewEventTest extends BaseTest {

    private LoginPage loginPage;
    private CreateEventPage createEventPage;

    private String eventName;
    private String eventPlace;
    private String eventStartDate = "05/05/2025";
    private String eventEndDate = "05/05/2026";
    private String ticketsPrice = "10";
    private String eventTotalTickets = "1000";

    @BeforeClass
    public void loginToApp() {
        loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        createEventPage = new CreateEventPage(driver);
    }

    @BeforeMethod
    public void clearData() {
        createEventPage.openPage();
        createEventPage.clearData();
    }


    @Test(priority = 1)
    public void TestingWithValidData() {
        eventName = TestDataGenerator.getRandomEventName();
        eventPlace = TestDataGenerator.getRandomEventPlace();

        createEventPage.enterName(eventName);
        createEventPage.enterPlace(eventPlace);
        createEventPage.enterStartDate(eventStartDate);
        createEventPage.enterEndDate(eventEndDate);
        createEventPage.enterPrice(ticketsPrice);
        createEventPage.enterTotalTickets(eventTotalTickets);
        createEventPage.clickCreate();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/All", "The event was not created");
        System.out.println("The event was created");
    }
    @Test(priority = 1)
    public void TestingTheNameFieldShort() {
        eventName = TestDataGenerator.getRandomEventNameInvalid();
        eventPlace = TestDataGenerator.getRandomEventPlace();

        createEventPage.enterName(eventName);
        createEventPage.enterPlace(eventPlace);
        createEventPage.enterStartDate(eventStartDate);
        createEventPage.enterEndDate(eventEndDate);
        createEventPage.enterPrice(ticketsPrice);
        createEventPage.enterTotalTickets(eventTotalTickets);
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
        eventPlace = TestDataGenerator.getRandomEventPlace();

        createEventPage.enterPlace(eventPlace);
        createEventPage.enterStartDate(eventStartDate);
        createEventPage.enterEndDate(eventEndDate);
        createEventPage.enterPrice(ticketsPrice);
        createEventPage.enterTotalTickets(eventTotalTickets);
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
        eventName = TestDataGenerator.getRandomEventName();
        eventPlace = TestDataGenerator.getRandomEventNameInvalid();

        createEventPage.enterName(eventName);
        createEventPage.enterPlace(eventPlace);
        createEventPage.enterStartDate(eventStartDate);
        createEventPage.enterEndDate(eventEndDate);
        createEventPage.enterPrice(ticketsPrice);
        createEventPage.enterTotalTickets(eventTotalTickets);
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
        eventName = TestDataGenerator.getRandomEventName();

        createEventPage.enterName(eventName);
        createEventPage.enterStartDate(eventStartDate);
        createEventPage.enterEndDate(eventEndDate);
        createEventPage.enterPrice(ticketsPrice);
        createEventPage.enterTotalTickets(eventTotalTickets);
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
        eventName = TestDataGenerator.getRandomEventName();
        eventPlace = TestDataGenerator.getRandomEventPlace();
        String zeroTickets = "0";

        createEventPage.enterName(eventName);
        createEventPage.enterPlace(eventPlace);
        createEventPage.enterStartDate(eventStartDate);
        createEventPage.enterEndDate(eventEndDate);
        createEventPage.enterPrice(ticketsPrice);
        createEventPage.enterTotalTickets(zeroTickets);
        createEventPage.clickCreate();

        if(driver.getCurrentUrl().equals("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create")){
            String errorMessage = createEventPage.getErrorMessageTotalTickets();
            Assert.assertFalse(errorMessage.isEmpty(), "Expected error message, but none found!");
            Assert.assertEquals(errorMessage, "Total Tickets must be a positive number and less than 1000.", "Error message did not match!");
            System.out.println("Error message: " + errorMessage);
        } else {
            Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create", "Event was created with 0 tickets");
        }
    }

    @Test(priority = 1)
    public void TestingThePriceTickets1100() {
        eventName = TestDataGenerator.getRandomEventName();
        eventPlace = TestDataGenerator.getRandomEventPlace();
        String highPrice = "1100";

        createEventPage.enterName(eventName);
        createEventPage.enterPlace(eventPlace);
        createEventPage.enterStartDate(eventStartDate);
        createEventPage.enterEndDate(eventEndDate);
        createEventPage.enterPrice(highPrice);
        createEventPage.enterTotalTickets(eventTotalTickets);
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
    @Test(priority = 2)
    public void TestingDates() {
        eventName = TestDataGenerator.getRandomEventName();
        eventPlace = TestDataGenerator.getRandomEventPlace();
        String wrongEndDate = "05/05/2023";

        createEventPage.enterName(eventName);
        createEventPage.enterPlace(eventPlace);
        createEventPage.enterStartDate(eventStartDate);
        createEventPage.enterEndDate(wrongEndDate);
        createEventPage.enterPrice(ticketsPrice);
        createEventPage.enterTotalTickets(eventTotalTickets);
        createEventPage.clickCreate();

        if (driver.getCurrentUrl().equals("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create")){
            String errorMessage = driver.findElement(By.xpath("/html/body/div/main/form/div/div[2]/div[2]/span")).getText();
            Assert.assertFalse(errorMessage.isEmpty(), "Expected error message, but none found!");
            Assert.assertEquals(errorMessage, "End Date must be after Start Date.", "Error message did not match!");
            System.out.println("Error message: " + errorMessage);
        } else {
            Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create", "Event was created with wrong end date");
        }
    }

    @Test(priority = 4)
    public void BackToList() {
        createEventPage.backToList();


        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/All", "Back to list unsuccessful");
        System.out.println("Back to list successful");
    }
}
