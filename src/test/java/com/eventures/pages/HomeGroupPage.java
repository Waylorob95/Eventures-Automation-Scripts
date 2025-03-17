package com.eventures.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomeGroupPage {

    private WebDriver driver;
    private String url = "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/";

    private By newEventLink = By.linkText("new event");
    private By viewAllEventsLink = By.linkText("all events");
    private By navLink = By.xpath("//li[@class=\"nav-item active\"]");
    private By createEventsNav = By.linkText("Create Event");
    private By viewAllEventsNav = By.linkText("All Events");
    private By logOutLink = By.xpath("/html/body/header/nav/div/div/ul[1]/li[2]/form/button");
    private By usernameText = By.xpath("/html/body/div/main/div/h1");
    private By numberEvents = By.xpath("/html/body/div/main/div/h5[1]/b");

    public HomeGroupPage(WebDriver driver) {
        this.driver = driver;
    }

    public void Open() {
        driver.get(url);
    }

    public void ClickNewEventLink() {
        driver.findElement(newEventLink).click();
    }

    public void ClickViewAllEventsLink() {
        driver.findElement(viewAllEventsLink).click();
    }

    public void ClickNavLink() {
        driver.findElement(navLink).click();
    }

    public String GetNumberEvents() {
       return driver.findElement(numberEvents).getText();
    }
    public void ClickCreateEventsNav() {
        driver.findElement(createEventsNav).click();
    }

    public void ClickViewAllEventsNav() {
        driver.findElement(viewAllEventsNav).click();
    }

    public String GetUsernameText() {
        return driver.findElement(usernameText).getText();
    }

    public void ClickLogOutLink() {
        driver.findElement(logOutLink).click();
    }

    public void logIn(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.enterUsername("guest");
        loginPage.enterPassword("guest");
        loginPage.clickLogin();
    }
}
