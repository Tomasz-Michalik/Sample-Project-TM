package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class accountPage {
    private WebDriver driver;

    public accountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = "div[class='text-sm-center'] a")
    WebElement signOutBtn;
    @FindBy(xpath = "//span[normalize-space()='Sign in']")
    WebElement signOutCheck;

    public void signOut(){
        signOutBtn.click();
    }

    public String signOutCheck() {
        return signOutCheck.getText();
    }



}
