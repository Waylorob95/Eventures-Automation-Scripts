import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class RegisterFormTest {

    WebDriver driver;


    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    public String randomName(int n){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int)(characters.length() * Math.random());
            sb.append(characters.charAt(index));
        }
        return sb.toString().toLowerCase();
    }

    @Test(priority = 1)
    public void UsernameWithFiveCharacters() throws InterruptedException {
        driver.navigate().to("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register");
        Thread.sleep(1000);
        driver.findElement(By.id("Input_Username")).sendKeys(randomName(5));
        driver.findElement(By.id("Input_Email")).sendKeys(randomName(5) + "@test.com");
        driver.findElement(By.id("Input_Password")).sendKeys("ardeq123");
        driver.findElement(By.id("Input_ConfirmPassword")).sendKeys("ardeq123");
        driver.findElement(By.id("Input_FirstName")).sendKeys("Ardes");
        driver.findElement(By.id("Input_LastName")).sendKeys("Simeonov");
        Thread.sleep(1000);

        System.out.println(driver.findElement(By.xpath("//button[@class=\"btn btn-primary\"]")).getText());
        driver.findElement(By.xpath("//button[@class=\"btn btn-primary\"]")).click();

        Thread.sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/");
    }

    @Test(priority = 2)
    public void UsernameWithFourCharacters() throws InterruptedException {
        driver.navigate().to("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register");
        Thread.sleep(1000);
        driver.findElement(By.id("Input_Username")).sendKeys(randomName(4));
        driver.findElement(By.id("Input_Email")).sendKeys(randomName(5) + "@test.com");
        driver.findElement(By.id("Input_Password")).sendKeys("ardeb123");
        driver.findElement(By.id("Input_ConfirmPassword")).sendKeys("ardeq123");
        driver.findElement(By.id("Input_FirstName")).sendKeys("Ardes");
        driver.findElement(By.id("Input_LastName")).sendKeys("Simeonov");
        driver.findElement(By.xpath("//button[@class=\"btn btn-primary\"]")).click();
        Thread.sleep(1000);

        String error = driver.findElement(By.xpath("//div[@class=\"text-danger validation-summary-errors\"]")).getText();
        String expectedError = "The field Username must be at least 5 characters long";
        System.out.println(error);
        Thread.sleep(1000);

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register");
        Assert.assertEquals(error, expectedError);
    }

    @Test(priority = 3)
    public void UsernameEmpty() throws InterruptedException {
        driver.navigate().to("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register");
        Thread.sleep(1000);
        driver.findElement(By.id("Input_Email")).sendKeys(randomName(5) + "@test.com");
        driver.findElement(By.id("Input_Password")).sendKeys("ardes123");
        driver.findElement(By.id("Input_ConfirmPassword")).sendKeys("ardes123");
        driver.findElement(By.id("Input_FirstName")).sendKeys("Ardes");
        driver.findElement(By.id("Input_LastName")).sendKeys("Simeonov");
        driver.findElement(By.xpath("//button[@class=\"btn btn-primary\"]")).click();

        String error = driver.findElement(By.xpath("//div[@class=\"text-danger validation-summary-errors\"]")).getText();
        String expectedError = "The Username field is required.";
        System.out.println(error);
        Thread.sleep(1000);

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register");
        Assert.assertEquals(error, expectedError);
    }

    @Test(priority = 2)
    public void UsernameTaken() throws InterruptedException {
        driver.navigate().to("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register");
        Thread.sleep(1000);

        driver.findElement(By.id("Input_Username")).sendKeys("guest");
        driver.findElement(By.id("Input_Email")).sendKeys(randomName(5) + "@test.com");
        driver.findElement(By.id("Input_Password")).sendKeys("ardes123");
        driver.findElement(By.id("Input_ConfirmPassword")).sendKeys("ardes123");
        driver.findElement(By.id("Input_FirstName")).sendKeys("Ardes");
        driver.findElement(By.id("Input_LastName")).sendKeys("Simeonov");
        driver.findElement(By.xpath("//button[@class=\"btn btn-primary\"]")).click();
        Thread.sleep(1000);

        String error = driver.findElement(By.xpath("//div[@class=\"text-danger validation-summary-errors\"]")).getText();
        String expectedError = "Username 'guest' is already taken.";
        System.out.println(error);
        Thread.sleep(1000);

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register");
        Assert.assertEquals(error, expectedError);
    }
}
