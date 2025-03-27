package com.eventures.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
    private By homeLink = By.linkText("Home");

    public CreateEventPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void clearData(){
        driver.findElement(By.id("Name")).clear();
        driver.findElement(By.id("Place")).clear();
        driver.findElement(By.id("PricePerTicket")).clear();
        driver.findElement(By.id("TotalTickets")).clear();
    }

    public void enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void enterPlace(String place) {
        driver.findElement(placeField).sendKeys(place);
    }

    public void enterStartDate(String startDate) {
        driver.findElement(startField).sendKeys(startDate);
    }

    public void enterEndDate(String endDate) {
        driver.findElement(endField).sendKeys(endDate);
    }

    public void enterPrice(String price) {
        driver.findElement(priceField).sendKeys(price);
    }

    public void enterTotalTickets(String totalTickets) {
        driver.findElement(totalTicketsField).sendKeys(totalTickets);
    }

    public void clickCreate() {
        driver.findElement(createButton).click();
    }

    public void backToList() {
        driver.findElement(backToListButton).click();
    }

    public String getErrorMessageName() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Name-error"))).getText();
        } catch (TimeoutException e) {
            return "";
        }
    }

    public String getErrorMessageTotalTickets() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PricePerTicket-error"))).getText();
        } catch (TimeoutException e) {
            return "";
        }
    }

    public String getErrorMessagePlace() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Place-error"))).getText();
        } catch (TimeoutException e) {
            return "";
        }
    }
    public void openPage() {
        driver.get(url);
    }

}
