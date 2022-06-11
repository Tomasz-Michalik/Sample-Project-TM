package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObject.homePage;

import java.util.concurrent.TimeUnit;

public class homePageTests {

    public static WebDriver driver;

    @BeforeEach
    void setUp(){
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mystore-testlab.coderslab.pl/index.php");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    void subscribeNewsletter() {
        homePage homepage = new homePage(driver);
        homepage.subscribeNewsletter();
        String alertExp = "You have successfully subscribed to this newsletter.";
        Assertions.assertAll(
                () -> Assertions.assertEquals(alertExp,  driver.findElement(By.cssSelector(".alert.alert-success")).getText()),
                () -> Assertions.assertTrue(driver.findElement(By.cssSelector(".alert.alert-success")).isEnabled()));

    }

    @Test
    void checkCurrencyUSD() {
        homePage homepage = new homePage(driver);
        homepage.changeCurrencyToUSD();
        Assertions.assertTrue(homepage.checkCurrency('$'));
    }

    @Test
    void checkCurrencyEUR() {
        homePage homepage = new homePage(driver);
        homepage.changeCurrencyToEUR();
        Assertions.assertTrue(homepage.checkCurrency('€'));
    }

    @Test
    void searchCheck() {
        homePage homepage = new homePage(driver);
        String check = homepage.searchItem(3);
        Assertions.assertEquals(check, homepage.searchCheck());
    }

    @Test
    void logoCheck() {
        homePage homepage = new homePage(driver);
        String firstLink = driver.getCurrentUrl();
        homepage.changeCurrencyToUSD().buttonClick();
        Assertions.assertEquals(firstLink,driver.getCurrentUrl());
    }

    @AfterEach
    void tearDown() {driver.quit();}
}
