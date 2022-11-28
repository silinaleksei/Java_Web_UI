package org.example.lesson5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DeletingPostTest extends AbstractTest {
    @Test
    public void deletePost() {
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
        WebElement post = getDriver().findElement(By.xpath("//a[text()='My first post']"));
        post.click();
        WebElement btnMenu = getDriver().findElement(By.cssSelector(".svgicon--more"));
        btnMenu.click();
        WebElement btnEdit = getDriver().findElement(By.xpath("//span[contains(.,'Редактировать запись')]"));
        btnEdit.click();
        WebElement btnDeletePost = getDriver().findElement(By.xpath("//footer/div/nav/a[1]"));
        btnDeletePost.click();
        WebElement btnDelete = getDriver().findElement(By.xpath("//button[2]/span"));
        new WebDriverWait (getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[2]/span")));
        btnDelete.click();
        Assertions.assertThrows(WebDriverException.class,
                () -> getDriver().findElement(By.xpath("//a[text()='My first post']")));
    }
}
