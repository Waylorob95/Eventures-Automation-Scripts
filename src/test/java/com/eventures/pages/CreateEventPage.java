package com.eventures.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateEventPage {

    private WebDriver driver;
    private String url = "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create";
    private By nameField = By.id("Name");
    private By placeField = By.id("Place");
    private By startField = By.id("Start");
    private By endField = By.id("End");
    private By priceField = By.id("PricePerTicket");
    private By totalTicketsField = By.id("TotalTickets");
    private By createButton = By.xpath("/html/body/div/main/form/div/div[4]/div/input");
    private By backToListButton = By.xpath("/html/body/div/main/form/div/div[4]/div/a");

    private By errorMessageName = By.id("Name-error");
    private By errorMessagePlace = By.id("Place-error");

    private By homeLink = By.linkText("Home");

    public CreateEventPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void Clear(){
        driver.findElement(By.id("Name")).clear();
        driver.findElement(By.id("Place")).clear();
        driver.findElement(By.id("PricePerTicket")).clear();
        driver.findElement(By.id("TotalTickets")).clear();
    }

    public void EnterName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void EnterPlace(String place) {
        driver.findElement(placeField).sendKeys(place);
    }

    public void EnterStartDate(String startDate) {
        driver.findElement(startField).sendKeys(startDate);
    }

    public void EnterEndDate(String endDate) {
        driver.findElement(endField).sendKeys(endDate);
    }

    public void EnterPrice(String price) {
        driver.findElement(priceField).sendKeys(price);
    }

    public void EnterTotalTickets(String totalTickets) {
        driver.findElement(totalTicketsField).sendKeys(totalTickets);
    }

    public void ClickCreate() {
        driver.findElement(createButton).click();
    }

    public void BackToList() {
        driver.findElement(backToListButton).click();
    }

    public String GetErrorMessageName() {
        return driver.findElement(errorMessageName).getText();
    }

    public String GetErrorMessagePlace() {
        return driver.findElement(errorMessagePlace).getText();
    }
    public void Open() {
        driver.get(url);
    }

    public void ClickHome() {
        driver.findElement(homeLink).click();
    }

}
