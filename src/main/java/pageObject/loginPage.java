package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPage {
    private WebDriver driver;

    public loginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = "input[class='form-control']")
    WebElement email;

    @FindBy(css = "input[name='password']")
    WebElement password;

    @FindBy(css = "#submit-login")
    WebElement signInButton;


    public void logInCheck() {
        email.sendKeys("test@test.tm");
        password.sendKeys("12345");
        signInButton.click();
    }
}
