package org.example.lesson6;

import io.qameta.allure.Attachment;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Story("Testing the LiveJournal website")
public class MyTest extends AbstractTest{
    @Test
    @DisplayName("Checking account login")
    @Attachment(value = "Page screenshot", type = "image/png", fileExtension = "png")
    void loginInTest() throws IOException {
        LoginPage loginPage = new LoginPage(getWebDriver());
        loginPage.loginIn("silin_aleksei", "1234As1234");
        loginPage.goToAccountPage();
        File file = MyUtils.makeScreenshot(getWebDriver(),
                "Page screenshot" + ".png");
        saveScreenshot(Files.readAllBytes(file.toPath()));
        Assertions.assertEquals("https://silin-aleksei.livejournal.com/",
                getWebDriver().getCurrentUrl());
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }
    @Test
    @Disabled
    void logOutTest() {
        LoginPage loginPage = new LoginPage(getWebDriver());
        loginPage.loginIn("silin_aleksei", "1234As1234");
        loginPage.logOut();
        Assertions.assertEquals("ВОЙТИ", loginPage.getBtnLogin().getText());
    }
    @Test
    @DisplayName("Checking the creation of a new post")
    void createPostTest() {
        AccountPage accountPage = new AccountPage(getWebDriver());
        LoginPage loginPage = new LoginPage(getWebDriver());
        loginPage.loginIn("silin_aleksei", "1234As1234");
        accountPage.createPost("This is text of the post", "My first post");
        loginPage.goToAccountPage();
        Assertions.assertEquals("My first post",
                getWebDriver().findElement(By.xpath("//a[text()='My first post']")).getText());
    }

    @Test
    @DisplayName("Checking post editing")
    void editPostTest() {
        AccountPage accountPage = new AccountPage(getWebDriver());
        LoginPage loginPage = new LoginPage(getWebDriver());
        loginPage.loginIn("silin_aleksei", "1234As1234");
        accountPage.createPost("This is text of the post", "My first post");
        loginPage.goToAccountPage();
        accountPage.editPost();
        Assertions.assertEquals("The text of the post is changed",
                getWebDriver().findElement(By.xpath("//*[text()='The text of the post is changed']")).getText());
    }

    @Test
    @DisplayName("Checking post deletion")
    void createAndDeletePostTest() {
        AccountPage accountPage = new AccountPage(getWebDriver());
        LoginPage loginPage = new LoginPage(getWebDriver());
        loginPage.loginIn("silin_aleksei", "1234As1234");
        accountPage.createPost("This is text of the post", "My first post");
        loginPage.goToAccountPage();
        accountPage.deletePost();
        Assertions.assertThrows(WebDriverException.class,
                () -> getWebDriver().findElement(By.xpath("//a[text()='My first post']")));
    }

    @Test
    @Disabled
    void deletePostTest() {
        AccountPage accountPage = new AccountPage(getWebDriver());
        LoginPage loginPage = new LoginPage(getWebDriver());
        loginPage.loginIn("silin_aleksei", "1234As1234");
        loginPage.goToAccountPage();
        accountPage.deletePost();
        Assertions.assertThrows(WebDriverException.class,
                () -> getWebDriver().findElement(By.xpath("//a[text()='My first post']")));
    }

    @Test
    void searchTest() throws InterruptedException {
        LJMainPage ljMainPage = new LJMainPage(getWebDriver());
        LoginPage loginPage = new LoginPage(getWebDriver());
        loginPage.loginIn("silin_aleksei", "1234As1234");
        loginPage.goToAccountPage();
        ljMainPage.toSearch("Новости");
        Assertions.assertEquals("Поиск по ЖЖ", getWebDriver().getTitle());
    }


}
