package com.eventures.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AllEventsPage {

    private WebDriver driver;
    private String url = "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/All";
    private By tableRows = By.xpath("//table/tbody/tr");

    private By userName = By.xpath("/html/body/header/nav/div/div/ul[1]/li[1]/a");

    private  boolean allOwnedEventsHaveButtons;
    private boolean noButtonsForNonOwners;

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
        allOwnedEventsHaveButtons = false;

        for (WebElement row : rows) {
            String owner = row.findElement(By.xpath("./td[last()-1]")).getText();
//            System.out.println(owner);

            if (owner.equals(loggedInUsername)) {
                boolean editButtonExists = !row.findElements(By.linkText("Edit")).isEmpty();
                boolean deleteButtonExists = !row.findElements(By.linkText("Delete")).isEmpty();

                if (editButtonExists && deleteButtonExists) {
                    allOwnedEventsHaveButtons = true;
                } else {
                    allOwnedEventsHaveButtons = false;
                    break;
                }
            }
        }
        return allOwnedEventsHaveButtons;
    }

    public boolean areEditDeleteButtonsAbsentForNonOwner(String loggedInUsername) {
        List<WebElement> rows = driver.findElements(tableRows);
        noButtonsForNonOwners = true;

        for (WebElement row : rows) {
            String owner = row.findElement(By.xpath("./td[last()-1]")).getText();
//            System.out.println(owner);

            if (!owner.equals(loggedInUsername)) {
                boolean editButtonExists = !row.findElements(By.linkText("Edit")).isEmpty();
                boolean deleteButtonExists = !row.findElements(By.linkText("Delete")).isEmpty();

                if (editButtonExists || deleteButtonExists) {
                    noButtonsForNonOwners = false;
                    break;
                }
            }
        }
        return noButtonsForNonOwners;
    }

    public void clickEditEvent(String eventName) {
        List<WebElement> rows = driver.findElements(tableRows);
        for (WebElement row : rows) {
            if (row.findElement(By.xpath("./td[1]")).getText().equals(eventName)) {
                row.findElement(By.linkText("Edit")).click();
            }
        }
    }

    public void clickDeleteEvent(String eventName) {

        List<WebElement> rows = driver.findElements(tableRows);
        for (WebElement row : rows) {
            if (row.findElement(By.xpath("./td[1]")).getText().equals(eventName)) {
                row.findElement(By.linkText("Delete")).click();
            }
        }
    }
}
