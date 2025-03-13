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

        createEventPage.EnterName(Utils.generateString(5));
        createEventPage.EnterPlace(Utils.generateString(7));
        createEventPage.EnterStartDate("05/05/2025");
        createEventPage.EnterEndDate("05/05/2026");
        createEventPage.EnterPrice("10");
        createEventPage.EnterTotalTickets("1000");
        createEventPage.ClickCreate();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/All");
    }
    @Test(priority = 1)
    public void TestingTheNameFieldShort() {
        createEventPage.Open();
        createEventPage.Clear();

        createEventPage.EnterName(Utils.generateString(2));
        createEventPage.EnterPlace(Utils.generateString(7));
        createEventPage.EnterStartDate("05/05/2025");
        createEventPage.EnterEndDate("05/05/2026");
        createEventPage.EnterPrice("10");
        createEventPage.EnterTotalTickets("1000");
        createEventPage.ClickCreate();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create");
        Assert.assertEquals(createEventPage.GetErrorMessageName(), "Name should be at least 3 characters long.");
    }

    @Test(priority = 1)
    public void TestingTheNameFieldEmpty() {
        createEventPage.Open();
        createEventPage.Clear();

        createEventPage.EnterPlace(Utils.generateString(7));
        createEventPage.EnterStartDate("05/05/2025");
        createEventPage.EnterEndDate("05/05/2026");
        createEventPage.EnterPrice("10");
        createEventPage.EnterTotalTickets("1000");
        createEventPage.ClickCreate();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create");
        Assert.assertEquals(createEventPage.GetErrorMessageName(), "The Name field is required");
    }

    @Test(priority = 2)
    public void TestingThePlaceFieldShort() {
        createEventPage.Open();
        createEventPage.Clear();

        createEventPage.EnterName(Utils.generateString(5));
        createEventPage.EnterPlace(Utils.generateString(2));
        createEventPage.EnterStartDate("05/05/2025");
        createEventPage.EnterEndDate("05/05/2026");
        createEventPage.EnterPrice("10");
        createEventPage.EnterTotalTickets("1000");
        createEventPage.ClickCreate();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create");
        Assert.assertEquals(createEventPage.GetErrorMessagePlace(), "Place should be at least 3 characters long.");
    }

    @Test(priority = 1)
    public void TestingThePlaceFieldEmpty() {
        createEventPage.Open();
        createEventPage.Clear();

        createEventPage.EnterName(Utils.generateString(5));
        createEventPage.EnterStartDate("05/05/2025");
        createEventPage.EnterEndDate("05/05/2026");
        createEventPage.EnterPrice("10");
        createEventPage.EnterTotalTickets("1000");
        createEventPage.ClickCreate();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create");
        Assert.assertEquals(createEventPage.GetErrorMessagePlace(), "The Place field is required");
    }

    @Test(priority = 1)
    public void TestingTheTotalTickets0() {
        createEventPage.Open();
        createEventPage.Clear();

        createEventPage.EnterName(Utils.generateString(5));
        createEventPage.EnterPlace(Utils.generateString(7));
        createEventPage.EnterStartDate("05/05/2025");
        createEventPage.EnterEndDate("05/05/2026");
        createEventPage.EnterPrice("10");
        createEventPage.EnterTotalTickets("0");
        createEventPage.ClickCreate();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create");
    }

    @Test(priority = 1)
    public void TestingThePriceTickets1100() {
        createEventPage.Open();
        createEventPage.Clear();

        createEventPage.EnterName(Utils.generateString(5));
        createEventPage.EnterPlace(Utils.generateString(7));
        createEventPage.EnterStartDate("05/05/2025");
        createEventPage.EnterEndDate("05/05/2026");
        createEventPage.EnterPrice("1100");
        createEventPage.EnterTotalTickets("1000");
        createEventPage.ClickCreate();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create");
    }
}
