package com.eventures.tests;

import com.eventures.base.BaseTest;
import com.eventures.pages.AllEventsPage;
import com.eventures.pages.LoginPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ViewAllEventsTest extends BaseTest {


    private LoginPage loginPage;
    private AllEventsPage allEventsPage;

    @BeforeClass
    public void loginToApp() {
        loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        allEventsPage = new AllEventsPage(driver);
    }

    @Test
    public void testEditOwnEvent() {
        allEventsPage.openPage();
        String userName = allEventsPage.getUserName();

        Assert.assertTrue(allEventsPage.areEditDeleteButtonsPresentForOwner(userName), "Only owned events can be edited/deleted!");
    }

    @Test
    public void testEditOtherEvent() {
        allEventsPage.openPage();
        String userName = allEventsPage.getUserName();

        Assert.assertTrue(allEventsPage.areEditDeleteButtonsAbsentForNonOwner(userName), "Only owned events can be edited/deleted!");
    }

    @Test
    public void testDeleteOwnEvent() {
        allEventsPage.openPage();
        String name = allEventsPage.getUserName();


        allEventsPage.clickDeleteEvent(name);
        driver.findElement(By.className("btn-primary")).click();
        Assert.assertFalse(allEventsPage.isEventPresent(name), "Event is not deleted");
    }

}
