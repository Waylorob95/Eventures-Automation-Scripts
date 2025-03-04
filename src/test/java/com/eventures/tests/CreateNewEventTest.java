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

    private static String eventName;
    private static String placeName;
    private static String startDate;
    private static String endDate;
    private static String price;
    private static String totalTickets;



    @BeforeClass
    public void loginToApp() {
        loginPage = new LoginPage(driver);
        loginPage.Open();
        loginPage.EnterUsername(username);
        loginPage.EnterPassword(password);
        loginPage.ClickLogin();

        createEventPage = new CreateEventPage(driver);
    }


    @Test(priority = 1)
    public void TestingWithValidData() {
        createEventPage.Open();
        createEventPage.Clear();

        eventName = Utils.generateString(5);
        placeName = Utils.generateString(7);
        startDate = "05/05/2025";
        endDate = "05/05/2026";
        price = "10";
        totalTickets = "1000";

        createEventPage.EnterName(eventName);
        createEventPage.EnterPlace(placeName);
        createEventPage.EnterStartDate(startDate);
        createEventPage.EnterEndDate(endDate);
        createEventPage.EnterPrice(price);
        createEventPage.EnterTotalTickets(totalTickets);
        createEventPage.ClickCreate();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/All");
    }
    @Test(priority = 1)
    public void TestingTheNameFieldShort() {
        createEventPage.Open();
        createEventPage.Clear();

        eventName = Utils.generateString(2);
        placeName = Utils.generateString(7);
        startDate = "05/05/2025";
        endDate = "05/05/2026";
        price = "10";
        totalTickets = "1000";

        createEventPage.EnterName(eventName);
        createEventPage.EnterPlace(placeName);
        createEventPage.EnterStartDate(startDate);
        createEventPage.EnterEndDate(endDate);
        createEventPage.EnterPrice(price);
        createEventPage.EnterTotalTickets(totalTickets);
        createEventPage.ClickCreate();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create");
        Assert.assertEquals(createEventPage.GetErrorMessageName(), "Name should be at least 3 characters long.");
    }

    @Test(priority = 1)
    public void TestingTheNameFieldEmpty() {
        createEventPage.Open();
        createEventPage.Clear();

        placeName = Utils.generateString(7);
        startDate = "05/05/2025";
        endDate = "05/05/2026";
        price = "10";
        totalTickets = "1000";

        createEventPage.EnterPlace(placeName);
        createEventPage.EnterStartDate(startDate);
        createEventPage.EnterEndDate(endDate);
        createEventPage.EnterPrice(price);
        createEventPage.EnterTotalTickets(totalTickets);
        createEventPage.ClickCreate();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create");
        Assert.assertEquals(createEventPage.GetErrorMessageName(), "The Name field is required");
    }

    @Test(priority = 2)
    public void TestingThePlaceFieldShort() {
        createEventPage.Open();
        createEventPage.Clear();

        eventName = Utils.generateString(5);
        placeName = Utils.generateString(2);
        startDate = "05/05/2025";
        endDate = "05/05/2026";
        price = "10";
        totalTickets = "1000";

        createEventPage.EnterName(eventName);
        createEventPage.EnterPlace(placeName);
        createEventPage.EnterStartDate(startDate);
        createEventPage.EnterEndDate(endDate);
        createEventPage.EnterPrice(price);
        createEventPage.EnterTotalTickets(totalTickets);
        createEventPage.ClickCreate();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create");
        Assert.assertEquals(createEventPage.GetErrorMessagePlace(), "Place should be at least 3 characters long.");
    }

    @Test(priority = 1)
    public void TestingThePlaceFieldEmpty() {
        createEventPage.Open();
        createEventPage.Clear();

        eventName = Utils.generateString(5);
        startDate = "05/05/2025";
        endDate = "05/05/2026";
        price = "10";
        totalTickets = "1000";

        createEventPage.EnterName(eventName);
        createEventPage.EnterStartDate(startDate);
        createEventPage.EnterEndDate(endDate);
        createEventPage.EnterPrice(price);
        createEventPage.EnterTotalTickets(totalTickets);
        createEventPage.ClickCreate();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create");
        Assert.assertEquals(createEventPage.GetErrorMessagePlace(), "The Place field is required");
    }

    @Test(priority = 1)
    public void TestingTheTotalTickets0() {
        createEventPage.Open();
        createEventPage.Clear();

        eventName = Utils.generateString(5);
        placeName = Utils.generateString(7);
        startDate = "05/05/2025";
        endDate = "05/05/2026";
        price = "10";
        totalTickets = "0";

        createEventPage.EnterName(eventName);
        createEventPage.EnterPlace(placeName);
        createEventPage.EnterStartDate(startDate);
        createEventPage.EnterEndDate(endDate);
        createEventPage.EnterPrice(price);
        createEventPage.EnterTotalTickets(totalTickets);
        createEventPage.ClickCreate();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create");
    }

    @Test(priority = 1)
    public void TestingThePriceTickets1100() {
        createEventPage.Open();
        createEventPage.Clear();

        eventName = Utils.generateString(5);
        placeName = Utils.generateString(7);
        startDate = "05/05/2025";
        endDate = "05/05/2026";
        price = "1100";
        totalTickets = "1000";

        createEventPage.EnterName(eventName);
        createEventPage.EnterPlace(placeName);
        createEventPage.EnterStartDate(startDate);
        createEventPage.EnterEndDate(endDate);
        createEventPage.EnterPrice(price);
        createEventPage.EnterTotalTickets(totalTickets);
        createEventPage.ClickCreate();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create");
    }
}
