package com.eventures.tests;

import com.eventures.base.BaseTest;
import com.eventures.pages.HomeGroupPage;
import com.eventures.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class HomeGroupTest extends BaseTest {
    private LoginPage loginPage;
    private HomeGroupPage homePage;

    @BeforeClass
    public void loginToApp() {
        loginPage = new LoginPage(driver);
        loginPage.Open();
        loginPage.EnterUsername(username);
        loginPage.EnterPassword(password);
        loginPage.ClickLogin();

        homePage = new HomeGroupPage(driver);
    }

    @Test(priority = 1)
    public void CreateNewEvent() {
        homePage.Open();
        homePage.ClickNewEventLink();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create");
    }

    @Test(priority = 1)
    public void ViewAllEvents() {
        homePage.Open();
        homePage.ClickViewAllEventsLink();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/All");
    }

    @Test(priority = 2)
    public void DropDownCreateEvent() {
        homePage.Open();
        homePage.ClickNavLink();
        homePage.ClickCreateEventsNav();


        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create");
    }

    @Test(priority = 2)
    public void DropDownViewAll() {
        homePage.Open();
        homePage.ClickNavLink();
        homePage.ClickViewAllEventsNav();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/All");
    }

    @Test(priority = 2)
    public void LogOut() {
        homePage.Open();
        homePage.ClickLogOutLink();

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/");
    }

    @Test(priority = 2)
    public void CheckUser() {
        homePage.Open();
        homePage.GetUsernameText();

        Assert.assertEquals(homePage.GetUsernameText(), "Welcome, " + username);
    }

    @Test(priority = 2)
    public void TestNumberOfEvents() {
        homePage.Open();
        int numberOfEvents = Integer.valueOf(homePage.GetNumberEvents());

        homePage.ClickViewAllEventsLink();
        List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
        int actualNumberOfEvents = rows.size();

        Assert.assertEquals(numberOfEvents, actualNumberOfEvents);
    }


}
