package com.eventures.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;

public class AllEventsPage {

    private WebDriver driver;
    private String url = "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/All";
    private By tableRows = By.xpath("//table/tbody/tr");

    private By userName = By.xpath("/html/body/header/nav/div/div/ul[1]/li[1]/a");

    private  boolean allOwnedEventsHaveButtons;
    private boolean noButtonsForNonOwners;

    private final Wait<WebDriver> wait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(10))
            .ignoring(StaleElementReferenceException.class);

    public AllEventsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get(url);
    }

    public List<String> getEventNames() {
        List<WebElement> rows = driver.findElements(tableRows);
        return rows.stream().map(row -> row.findElement(By.xpath("./td[1]")).getText()).toList();
    }

    public List<String> getOwners(){
        List<WebElement> rows = driver.findElements(tableRows);
        return rows.stream().map(row -> row.findElement(By.xpath("./td[last()-1]")).getText()).toList();
    }

    public boolean isEventPresent(String eventName) {
        return getEventNames().contains(eventName);
    }

    public String getUserName() {
        String greetMsg = driver.findElement(userName).getText();
        String userName = greetMsg.substring(greetMsg.indexOf(" ") + 1);
        String regex = "[^a-zA-Z0-9\\s]";

        return userName.replaceAll(regex, "");
    }

    public boolean areEditDeleteButtonsPresentForOwner(String loggedInUsername) {
        List<WebElement> rows = driver.findElements(tableRows);

        for (WebElement row : rows) {
            String owner = row.findElement(By.xpath("./td[last()-1]")).getText();

            try {
                if (owner.equals(loggedInUsername)) {
                    boolean hasEditButton = row.findElements(By.xpath(".//a[contains(text(),'Edit')]")).size() > 0;
                    boolean hasDeleteButton = row.findElements(By.xpath(".//a[contains(text(),'Delete')]")).size() > 0;

                    if (!hasEditButton || !hasDeleteButton) {
                        return false;
                    }
                }
            } catch (StaleElementReferenceException | NoSuchElementException e) {
                return false;
            }
        }
        return true;
    }

    public boolean areEditDeleteButtonsAbsentForNonOwner(String loggedInUsername) {
        List<WebElement> rows = driver.findElements(tableRows);

        for (WebElement row : rows) {
            String owner = row.findElement(By.xpath("./td[last()-1]")).getText();

            try{
                if (!owner.equals(loggedInUsername)) {
                    if (row.findElements(By.xpath(".//a[contains(.,'Edit') or contains(.,'Delete')]")).size() > 0) {
                        return false;
                    }
                }
            } catch (StaleElementReferenceException | NoSuchElementException e){
                return false;
            }
        }
        return true;
    }

    public void clickDeleteEvent(String loggedInUser) {
        List<WebElement> rows = driver.findElements(tableRows);

            for (WebElement row : rows) {
                try {
                    String owner = row.findElement(By.xpath("./td[last()-1]")).getText();
                    String eventName = row.findElement(By.xpath("./td[1]")).getText();

                    if (owner.equals(loggedInUser)) {
                        System.out.println("User is the owner:  " + owner + ". Proceeding to deleting...");
                        System.out.println("Deleting event: " + eventName);
                        WebElement deleteButton = row.findElement(By.linkText("Delete"));
                        deleteButton.click();
                        break;
                    }
                } catch (StaleElementReferenceException | NoSuchElementException e) {
                    System.out.println("No element found. Aborting...");
                    break;
                }
            }
    }


    public void clickEditEvent(String eventName) {
        List<WebElement> rows = driver.findElements(tableRows);

        for (WebElement row : rows) {
            try {
                WebElement eventCell = row.findElement(By.xpath("./td[1]"));
                if (eventCell.getText().equals(eventName)) {
                    WebElement deleteButton = row.findElement(By.linkText("Edit"));
                    deleteButton.click();
                }
            } catch (StaleElementReferenceException | NoSuchElementException e) {
                break;
            }
        }
    }
}
