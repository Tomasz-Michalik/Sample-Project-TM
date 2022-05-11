import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;
import pageObject.homePage;

public class tests {

    public static WebDriver driver;

    @BeforeEach
    void setUp(){
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mystore-testlab.coderslab.pl/");
    }

    @Test
    void subscribeNewsletter() {
        homePage homepage = new homePage(driver);
        homepage.subscribeNewsletter();
        String alertExp = "You have successfully subscribed to this newsletter.";
        Assertions.assertEquals(alertExp,  homepage.newsAlertText());
        Assertions.assertTrue(homepage.newsAlertEnabled());
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

    @AfterEach
    void tearDown() {driver.quit();}
}
