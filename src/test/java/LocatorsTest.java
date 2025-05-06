
import static org.testng.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LocatorsTest {

    private WebDriver driver;

    @BeforeMethod
    void initBrowser() {
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        //implicit wait - waiting for objects to show in the page
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod
    void quitBrowser() {
        driver.quit();
    }

    @Test
    void elementsExist() throws InterruptedException {
        driver.findElement(By.id("inputUsername")).sendKeys("myuser");
        driver.findElement(By.name("inputPassword")).sendKeys("mypassword");
        driver.findElement(By.className("signInBtn")).click();
        System.out.println(driver.findElement(By.cssSelector("p.error")).getText());

        driver.findElement(By.linkText("Forgot your password?")).click();
        // Wait due to changing state in page, not clickable yet until it is in stable state
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("myuser");
        driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("myuser@mail.com");
        driver.findElement(By.xpath("//input[@type='text'][2]")).clear();
        driver.findElement(By.cssSelector("input[type='text']:nth-child(3)")).sendKeys("mymail@mail.com");
        driver.findElement(By.xpath("//form/input[3]")).sendKeys("123123123");

        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".reset-pwd-btn")).click();
        System.out.println(driver.findElement(By.cssSelector("form p")).getText());
        driver.findElement(By.xpath("//div[@class='forgot-pwd-btn-conainer']/button[1]")).click();

        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#inputUsername")).sendKeys("useruser");
        driver.findElement(By.cssSelector("input[type*='pass']")).sendKeys("pass123");
        driver.findElement(By.id("chkboxOne")).click();
        driver.findElement(By.xpath("//button[contains(@class,'submit')]")).click();
    }

    @Test
    void successfulLogin() throws InterruptedException {
        String name = "myName";
        driver.findElement(By.id("inputUsername")).sendKeys(name);
        driver.findElement(By.name("inputPassword")).sendKeys("rahulshettyacademy");
        driver.findElement(By.className("signInBtn")).click();
        Thread.sleep(2000);
        assertEquals(driver.findElement(By.tagName("p")).getText(), "You are successfully logged in.");
        assertEquals(driver.findElement(By.cssSelector("div[class='login-container'] h2")).getText(), "Hello " + name + ",");
    }
}
