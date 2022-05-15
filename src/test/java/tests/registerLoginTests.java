package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObject.accountPage;
import pageObject.homePage;
import pageObject.loginPage;
import pageObject.registerPage;

public class registerLoginTests {

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
    void login() {
        homePage homepage = new homePage(driver);
        homepage.signIn();
        loginPage loginpage = new loginPage(driver);
        loginpage.logInCheck();
        Assertions.assertEquals("test test", homepage.accountInfo());
    }
    @Test
    void signOut() {
        login();
        accountPage accountPage = new accountPage(driver);
        accountPage.signOut();
    }
    @Test
    void register() throws InterruptedException {
        homePage homePage = new homePage(driver);
        homePage.signIn();
        loginPage loginPage = new loginPage(driver);
        loginPage.noAccClick();
        registerPage registerPage =  new registerPage(driver);
        registerPage.register();
        Assertions.assertTrue(registerPage.registerCheck());
    }

    @AfterEach
    void tearDown() {driver.quit();}
}
