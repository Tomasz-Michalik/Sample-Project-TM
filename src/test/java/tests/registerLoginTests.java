package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObject.accountPage;
import pageObject.homePage;
import pageObject.loginPage;
import pageObject.registerPage;

import java.util.concurrent.TimeUnit;

public class registerLoginTests {

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
    void login() {
        homePage homepage = new homePage(driver);
        homepage.signIn().logInCheck();
        Assertions.assertEquals("test test", driver.findElement(By.cssSelector("a[class='account']")).getText());
    }
    @Test
    void signOut() {
        login();
        accountPage accountPage = new accountPage(driver);
        accountPage.signOut();
    }
    @Test
    void register() {
        homePage homePage = new homePage(driver);
        homePage.signIn().noAccClick().register();
        registerPage registerPage = new registerPage(driver);
        Assertions.assertTrue(registerPage.registerCheck());
    }

    @AfterEach
    void tearDown() {driver.quit();}
}
