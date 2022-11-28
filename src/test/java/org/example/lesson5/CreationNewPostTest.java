package org.example.lesson5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreationNewPostTest extends AbstractTest {
    @Test
    public void creationNewPost() {
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

        WebElement btnCreatePost = getDriver().findElement(By.xpath("//header/div[1]/nav[2]/ul/li[4]/a/span[1]"));
        btnCreatePost.click();
        WebElement inputTextPost = getDriver().findElement(By.cssSelector(".public-DraftStyleDefault-ltr"));
        inputTextPost.sendKeys("This is text of the post");
        WebElement inputPostTitle = getDriver().findElement(By.xpath("//*[@id='content']//div[2]/textarea"));
        inputPostTitle.sendKeys("My first post");
        WebElement btnConfigureAndPublish = getDriver().findElement(By.cssSelector(".backgroundBlue-0-2-104.textWithDropDownIcon-0-2-115"));
        btnConfigureAndPublish.click();
        WebElement btnPublish = getDriver().findElement(By.xpath("//footer/div/button/span"));
        btnPublish.click();
        WebElement btnUser = getDriver().findElement(By.xpath("//*[text()='silin_aleksei']"));
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='silin_aleksei']")));
        btnUser.click();
        Assertions.assertEquals("My first post",
                getDriver().findElement(By.xpath("//a[text()='My first post']")).getText());
    }
}
