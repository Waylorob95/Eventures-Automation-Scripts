import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginFormTest {

    WebDriver driver;
    WebElement username;
    WebElement password;
    WebElement loginButton;
    WebElement registerLink;

    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/");
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @Test(priority = 1)
    public void LoginWithValidCredentials() throws InterruptedException{
        driver.navigate().to("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Login");
        Thread.sleep(1000);

        username = driver.findElement(By.id("Input_Username"));
        password = driver.findElement(By.id("Input_Password"));
        username.sendKeys("guest");
        password.sendKeys("guest");
        Thread.sleep(1000);

        loginButton = driver.findElement(By.xpath("//button[@class=\"btn btn-primary\"]"));
        loginButton.click();

        System.out.println("You are logging in");

        Thread.sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/");

    }

    @Test(priority = 2)
    public void LoginWithInvalidCredentials() throws InterruptedException{
        driver.navigate().to("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Login");
        Thread.sleep(1000);

        username = driver.findElement(By.id("Input_Username"));
        password = driver.findElement(By.id("Input_Password"));
        username.sendKeys("guest");
        password.sendKeys("error");
        Thread.sleep(1000);

        loginButton = driver.findElement(By.xpath("//button[@class=\"btn btn-primary\"]"));
        loginButton.click();
        Thread.sleep(1000);

        String errorMsg = driver.findElement(By.xpath("//div[@class=\"text-danger validation-summary-errors\"]")).getText();
        System.out.println(errorMsg);
        Thread.sleep(1000);

        Assert.assertEquals(errorMsg, "Invalid login attempt.");
        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Login");
    }

    @Test(priority = 3)
    public void LoginWithEmptyCredentials() throws InterruptedException{
        driver.navigate().to("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Login");
        Thread.sleep(1000);

        username = driver.findElement(By.id("Input_Username"));
        username.sendKeys("guest");
        Thread.sleep(1000);

        loginButton = driver.findElement(By.xpath("//button[@class=\"btn btn-primary\"]"));
        loginButton.click();
        Thread.sleep(1000);

        String errorMsg = driver.findElement(By.xpath("//div[@class=\"text-danger validation-summary-errors\"]")).getText();
        System.out.println(errorMsg);
        Thread.sleep(1000);

        Assert.assertEquals(errorMsg, "The Password field is required.");
        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Login");
    }

    @Test(priority = 2)
    public void RegisterLink() throws InterruptedException{
        driver.navigate().to("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Login");

        registerLink = driver.findElement(By.linkText("Register as a new user"));
        registerLink.click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register");
    }
}
