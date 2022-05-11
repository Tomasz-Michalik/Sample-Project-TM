package pageObject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class homePage {
    private WebDriver driver;

    public homePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "email")
    WebElement newsletterEmail;

    @FindBy(name = "submitNewsletter")
    WebElement newsletterButton;

    @FindBy(css = ".alert.alert-success")
    WebElement newsletterAlert;

    @FindBy(css = ".expand-more._gray-darker")
    WebElement currencyBtn;

    @FindBy(css = "a[title='US Dollar']")
    WebElement usdButton;

    @FindBy(css = "a[title='Euro']")
    WebElement eurButton;

    @FindBy(className = "price")
    WebElement price;

    @FindBy(className = "product-title")
    List<WebElement> items;

    @FindBy(name = "s")
    WebElement searchItems;

    public String randomMail() {
        Random random = new Random();
        return "testMail" + random.nextInt(1000000) + "@test.tm";
    }

    public void subscribeNewsletter() {
        newsletterEmail.sendKeys(randomMail());
        newsletterButton.click();
    }

    public boolean newsAlertEnabled() {
        return newsletterAlert.isDisplayed();
    }
    public String newsAlertText() {
        return newsletterAlert.getText();
    }
    public void changeCurrencyToUSD() throws InterruptedException {
        currencyBtn.click();
        Thread.sleep(1000);
        usdButton.click();
    }
    public void changeCurrencyToEUR() throws InterruptedException {
        currencyBtn.click();
        Thread.sleep(1000);
        eurButton.click();
    }
    public boolean checkCurrency(char currency) {
        char firstChar = price.getText().charAt(0);
        if (currency == firstChar) {
            return true;
        } else {
            return false;
        }
    }

}
