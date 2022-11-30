package org.example.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends AbstractPage {

    public WebElement getBtnLogin() {
        return btnLogin;
    }

    @FindBy(css = ".s-header-item__link--login")
    private WebElement btnLogin;

    @FindBy(css = "#user")
    private WebElement inputUsername;

    @FindBy(css = "#lj_loginwidget_password")
    private WebElement inputPassword;

    @FindBy(css = ".b-loginform-btn--auth")
    private WebElement btnInput;

    @FindBy(css = ".s-nav-item__name ")
    private WebElement btnUser;

    @FindBy(css = "a.s-header-sub-list-item__link--logout")
    private WebElement btnLogout;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage setLogin(String login) {
        this.inputUsername.sendKeys(login);
        return this;
    }

    public LoginPage setPassword(String password) {
        this.inputPassword.click();
        this.inputPassword.sendKeys(password);
        return this;
    }

    public void loginIn() {
        this.btnInput.click();
    }

    public void goToAccountPage() {
        this.btnUser.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("silin-aleksei.livejournal.com/"));
    }
    public void logOut(){
        Actions logOut = new Actions(getDriver());
        logOut.moveToElement(btnUser)
                .pause(Duration.ofSeconds(1))
                .click(btnLogout)
                .build()
                .perform();

    }

    public void loginIn(String login, String password) {

        Actions loginIn = new Actions(getDriver());
        loginIn.click(btnLogin)
                .sendKeys(this.inputUsername, login)
                .click(this.inputPassword)
                .sendKeys(password)
                .click(this.btnInput)
                .build()
                .perform();
    }
}
