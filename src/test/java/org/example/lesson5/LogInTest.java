package org.example.lesson5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class LogInTest extends AbstractTest{
    @Test
    public void logIn() {
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
        WebElement btnUser = getDriver().findElement(By.cssSelector(".s-nav-item__name "));
        btnUser.click();
        Assertions.assertEquals("https://silin-aleksei.livejournal.com/",
                getDriver().getCurrentUrl());
    }
}
