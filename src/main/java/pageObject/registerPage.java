package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class registerPage {
    private WebDriver driver;

    public registerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "label.radio-inline")
    WebElement socialTitle;

    @FindBy(name = "firstname")
    WebElement firstName;

    @FindBy(name = "lastname")
    WebElement lastName;

    @FindBy(name = "email")
    WebElement email;

    @FindBy(name = "password")
    WebElement password;

    @FindBy(css = "button.btn.btn-primary.form-control-submit.float-xs-right")
    WebElement saveButton;

//    @FindBy(css = ".alert.alert-danger")
//    WebElement emailAlert;

    @FindBy(css = "a.account>span")
    WebElement accName;

    public String randomMail() {
        Random random = new Random();
        return "testRegister" + random.nextInt(10000000) + "@tm.tm";
    }
    public void register() {
        socialTitle.click();
        firstName.clear();firstName.sendKeys("Jan");
        lastName.clear();lastName.sendKeys("Kowalski");
        email.clear();email.sendKeys(randomMail());
        password.clear();password.sendKeys("password");
        saveButton.click();
        emailCheck();
    }
    void emailCheck() {
        List<WebElement> emailAlert = driver.findElements(By.cssSelector(".alert.alert-danger"));
        if(emailAlert.size() != 0){
            register();
        }
        else{}
    }
    public boolean registerCheck() {
        if (accName.getText().equals("Jan Kowalski")) return true;
        else return false;

    }

}
