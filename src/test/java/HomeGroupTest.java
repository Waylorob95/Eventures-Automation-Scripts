import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HomeGroupTest {

    WebDriver driver;

    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Login");
        driver.manage().window().maximize();
        driver.findElement(By.id("Input_Username")).sendKeys("guest");
        driver.findElement(By.id("Input_Password")).sendKeys("guest");
        driver.findElement(By.xpath("//button[@class=\"btn btn-primary\"]")).click();
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @Test(priority = 1)
    public void CreateNewEvent() throws InterruptedException {
        driver.navigate().to("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/");
        driver.findElement(By.linkText("new event")).click();
        System.out.println("Create new event tab");
        Thread.sleep(2000);

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create");
    }

    @Test(priority = 1)
    public void ViewAllEvents() throws InterruptedException {
        driver.navigate().to("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/");
        driver.findElement(By.linkText("all events")).click();
        System.out.println("See all events tab");
        Thread.sleep(2000);

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/All");
    }

    @Test(priority = 2)
    public void DropDownCreateEvent() throws InterruptedException {
        driver.navigate().to("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/");
        driver.findElement(By.xpath("//li[@class=\"nav-item active\"]")).click();
        Thread.sleep(1000);
        driver.findElement(By.linkText("Create Event")).click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create");
    }

    @Test(priority = 2)
    public void DropDownViewAll() throws InterruptedException {
        driver.navigate().to("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/");
        driver.findElement(By.xpath("//li[@class=\"nav-item active\"]")).click();
        Thread.sleep(1000);
        driver.findElement(By.linkText("All Events")).click();
        System.out.println("See all events tab");
        Thread.sleep(2000);

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/All");
    }
}
