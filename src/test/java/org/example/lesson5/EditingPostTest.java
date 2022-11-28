package org.example.lesson5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EditingPostTest extends AbstractTest {
    @Test
    public void editPost() {
        WebElement btnLogin = getDriver().findElement(By.cssSelector(".s-header-item__link--login"));
        btnLogin.click();
        Actions actions = new Actions(getDriver());
        actions.click(getDriver().findElement(By.cssSelector("#user")))
                .sendKeys("silin_aleksei")
                .click(getDriver().findElement(By.cssSelector("#lj_loginwidget_password")))
                .sendKeys("1234As1234")
                .click(getDriver().findElement(By.cssSelector(".b-loginform-btn--auth")))
                .pause(Duration.ofSeconds(2))
                .build()
                .perform();

        WebElement btnUser = getDriver().findElement(By.cssSelector(".s-nav-item__name "));
        btnUser.click();
        WebElement post = getDriver().findElement(By.xpath("//a[text()='My first post']"));
        post.click();
        WebElement btnMenu = getDriver().findElement(By.cssSelector(".svgicon--more"));
        btnMenu.click();
        WebElement btnEdit = getDriver().findElement(By.xpath("//span[contains(.,'Редактировать запись')]"));
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(btnEdit));
        btnEdit.click();
        WebElement inputTextPost = getDriver().findElement(By.cssSelector(".public-DraftStyleDefault-ltr"));
        new WebDriverWait(getDriver(),Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(inputTextPost));
        getDriver().findElement(By.xpath("//*[@id='content']//div[2]/textarea"))
                .sendKeys(Keys.ENTER, "The text of the post is changed");
        WebElement btnConfigureAndPublish = getDriver().findElement(By.cssSelector(".backgroundBlue-0-2-104.textWithDropDownIcon-0-2-115"));
        btnConfigureAndPublish.click();
        WebElement btnPublish = getDriver().findElement(By.xpath("//footer/div/button/span"));
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//footer/div/button/span")));
        btnPublish.click();
        WebElement inputEditText = getDriver().findElement(By.xpath("//*[text()='The text of the post is changed']"));
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='The text of the post is changed']")));
        inputEditText.click();
        Assertions.assertEquals("The text of the post is changed",
                getDriver().findElement(By.xpath("//*[text()='The text of the post is changed']")).getText());
    }


}
