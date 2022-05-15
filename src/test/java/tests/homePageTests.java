package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObject.homePage;

public class homePageTests {

    public static WebDriver driver;

    @BeforeEach
    void setUp(){
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mystore-testlab.coderslab.pl/index.php");
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
    void checkCurrencyUSD() throws InterruptedException {
        homePage homepage = new homePage(driver);
        homepage.changeCurrencyToUSD();
        Assertions.assertTrue(homepage.checkCurrency('$'));
    }

    @Test
    void checkCurrencyEUR() throws InterruptedException {
        homePage homepage = new homePage(driver);
        homepage.changeCurrencyToEUR();
        Assertions.assertTrue(homepage.checkCurrency('€'));
    }

    @Test
    void searchCheck() throws InterruptedException {
        homePage homepage = new homePage(driver);
        String check = homepage.searchItem(3);
        Thread.sleep(1500);
        Assertions.assertEquals(check, homepage.searchCheck());
    }

    @Test
    void logoCheck() throws InterruptedException {
        homePage homepage = new homePage(driver);
        String firstLink = driver.getCurrentUrl();
        homepage.changeCurrencyToUSD();
        homepage.buttonClick();
        Assertions.assertEquals(firstLink,driver.getCurrentUrl());
    }

    @AfterEach
    void tearDown() {driver.quit();}
}
