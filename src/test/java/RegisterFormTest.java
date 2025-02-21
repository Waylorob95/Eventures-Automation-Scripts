import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class RegisterFormTest {

    WebDriver driver;
    WebElement username;
    WebElement email;
    WebElement password;
    WebElement confirmPassword;
    WebElement firstName;
    WebElement LastName;
    WebElement button;


    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register");
        username = driver.findElement(By.id("Input_Username"));
        email = driver.findElement(By.id("Input_Email"));
        password = driver.findElement(By.id("Input_Password"));
        confirmPassword = driver.findElement(By.id("Input_ConfirmPassword"));
        firstName = driver.findElement(By.id("Input_FirstName"));
        LastName = driver.findElement(By.id("Input_LastName"));
        button = driver.findElement(By.xpath("//button[@class=\"btn btn-primary\"]"));
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void UsernameWithFiveCharacters() throws InterruptedException {
        username.sendKeys("ardey");
        email.sendKeys("ardey@test.com");
        password.sendKeys("ardeq123");
        confirmPassword.sendKeys("ardeq123");
        firstName.sendKeys("Ardes");
        LastName.sendKeys("Simeonov");

        System.out.println(button.getText());
        button.click();

        Thread.sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/");
    }

    @Test
    public void UsernameWithFourCharacters() throws InterruptedException {
        username.sendKeys("ardb");
        email.sendKeys("ardeb@test.com");
        password.sendKeys("ardeb123");
        confirmPassword.sendKeys("ardeq123");
        firstName.sendKeys("Ardes");
        LastName.sendKeys("Simeonov");
        button.click();

        Thread.sleep(1000);

        String error = driver.findElement(By.xpath("//div[@class=\"text-danger validation-summary-errors\"]")).getText();
        String expectedError = "The field Username must be at least 5 characters long";
        System.out.println(error);
        Thread.sleep(1000);

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register");
        Assert.assertEquals(error, expectedError);
    }

    @Test
    public void UsernameEmpty() throws InterruptedException {
        email.sendKeys("ardes@test.com");
        password.sendKeys("ardes123");
        confirmPassword.sendKeys("ardes123");
        firstName.sendKeys("Ardes");
        LastName.sendKeys("Simeonov");
        button.click();

        Thread.sleep(1000);

        String error = driver.findElement(By.xpath("//div[@class=\"text-danger validation-summary-errors\"]")).getText();
        String expectedError = "The Username field is required.";
        System.out.println(error);
        Thread.sleep(1000);

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register");
        Assert.assertEquals(error, expectedError);
    }

    @Test
    public void UsernameTaken() throws InterruptedException {
        username.sendKeys("ardes123");
        email.sendKeys("ardes@test.com");
        password.sendKeys("ardes123");
        confirmPassword.sendKeys("ardes123");
        firstName.sendKeys("Ardes");
        LastName.sendKeys("Simeonov");
        button.click();

        Thread.sleep(1000);

        String error = driver.findElement(By.xpath("//div[@class=\"text-danger validation-summary-errors\"]")).getText();
        String expectedError = "Username 'ardes123' is already taken.";
        System.out.println(error);
        Thread.sleep(1000);

        Assert.assertEquals(driver.getCurrentUrl(), "http://softuni-qa-loadbalancer-2137572849.eu-north-1.elb.amazonaws.com:81/Identity/Account/Register");
        Assert.assertEquals(error, expectedError);
    }
}
