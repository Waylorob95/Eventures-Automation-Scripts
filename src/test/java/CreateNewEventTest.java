import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CreateNewEventTest {
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

    public void Clear(){
        driver.findElement(By.id("Name")).clear();
        driver.findElement(By.id("Place")).clear();
        driver.findElement(By.id("PricePerTicket")).clear();
        driver.findElement(By.id("TotalTickets")).clear();
    }

    public String randomName(int n){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int)(characters.length() * Math.random());
            if(i != 0){
                sb.append(Character.toLowerCase(characters.charAt(index)));
            } else {
                sb.append(characters.charAt(index));
            }

        }
        return sb.toString();
    }

    @Test(priority = 1)
    public void TestingTheNameField2() throws InterruptedException{
        driver.navigate().to("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create");
        Clear();
        driver.findElement(By.id("Name")).sendKeys(randomName(2));
        Thread.sleep(1000);
        driver.findElement(By.id("Place")).sendKeys("Burgas");
        Thread.sleep(1000);
        driver.findElement(By.id("Start")).sendKeys("01/05/2025");
        driver.findElement(By.id("End")).sendKeys("01/03/2026");
        Thread.sleep(1000);
        driver.findElement(By.id("PricePerTicket")).sendKeys("10");
        driver.findElement(By.id("TotalTickets")).sendKeys("1000");
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div/main/form/div/div[4]/div/input")).click();
        Thread.sleep(1500);

        String errorMsg = driver.findElement(By.xpath("/html/body/div/main/form/div/div[1]/div[1]/span")).getText();
        System.out.println(errorMsg);

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create");
        Assert.assertEquals(errorMsg, "Name should be at least 3 characters long.");
    }

    @Test(priority = 1)
    public void TestingTheNameField3() throws InterruptedException{
        driver.navigate().to("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create");
        Clear();
        driver.findElement(By.id("Name")).sendKeys(randomName(3));
        Thread.sleep(1000);
        driver.findElement(By.id("Place")).sendKeys("Burgas");
        Thread.sleep(1000);
        driver.findElement(By.id("Start")).sendKeys("01/05/2025");
        driver.findElement(By.id("End")).sendKeys("01/03/2026");
        Thread.sleep(1000);
        driver.findElement(By.id("PricePerTicket")).sendKeys("10");
        driver.findElement(By.id("TotalTickets")).sendKeys("1000");
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div/main/form/div/div[4]/div/input")).click();
        Thread.sleep(1500);


        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/All");
    }

    @Test(priority = 2)
    public void TestingTheNameField50() throws InterruptedException{
        driver.navigate().to("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/Create");
        Clear();
        driver.findElement(By.id("Name")).sendKeys(randomName(50));
        Thread.sleep(1000);
        driver.findElement(By.id("Place")).sendKeys("Burgas");
        Thread.sleep(1000);
        driver.findElement(By.id("Start")).sendKeys("01/05/2025");
        driver.findElement(By.id("End")).sendKeys("01/03/2026");
        Thread.sleep(1000);
        driver.findElement(By.id("PricePerTicket")).sendKeys("10");
        driver.findElement(By.id("TotalTickets")).sendKeys("1000");
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div/main/form/div/div[4]/div/input")).click();
        Thread.sleep(1500);


        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Events/All");
    }
}
