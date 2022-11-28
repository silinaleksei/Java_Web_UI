package org.example.lesson5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class LogOutTest extends AbstractTest{
    @Test
    public void logOut() {
        WebElement btnLogin = getDriver().findElement(By.cssSelector(".s-header-item__link--login"));
        btnLogin.click();
        Actions actions = new Actions(getDriver());
        actions.click(getDriver().findElement(By.cssSelector("#user")))
                .sendKeys("silin_aleksei")
                .click(getDriver().findElement(By.cssSelector("#lj_loginwidget_password")))
                .sendKeys("1234As1234")
                .click(getDriver().findElement(By.cssSelector(".b-loginform-btn--auth")))
                .pause(Duration.ofSeconds(1))
                .build()
                .perform();
        actions.moveToElement(getDriver().findElement(By.cssSelector(".s-nav-item__name ")))
                .pause(Duration.ofSeconds(2))
                .click(getDriver().findElement(By.cssSelector("a.s-header-sub-list-item__link--logout")))
                .build()
                .perform();
        Assertions.assertEquals("ВОЙТИ",getDriver()
                .findElement(By.cssSelector(".s-header-item__link--login")).getText());

    }
}
