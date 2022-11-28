package org.example.lesson5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AuthorizationTest extends AbstractTest{
    @Test
    public void logIn() {
        WebElement btnLogin = getDriver().findElement(By.cssSelector(".s-header-item__link--login"));
        btnLogin.click();
        WebElement inputUsername = getDriver().findElement(By.cssSelector("#user"));
        inputUsername.sendKeys("silin_aleksei");
        WebElement inputPassword = getDriver().findElement(By.cssSelector("#lj_loginwidget_password"));
        inputPassword.sendKeys("1234As1234");
        WebElement btnInput = getDriver().findElement(By.cssSelector(".b-loginform-btn--auth"));
        btnInput.click();
        WebElement btnUser = getDriver().findElement(By.cssSelector(".s-nav-item__name "));
        btnUser.click();
        Assertions.assertEquals("https://silin-aleksei.livejournal.com/",
                getDriver().getCurrentUrl());

    }
}
