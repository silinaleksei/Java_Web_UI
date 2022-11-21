package org.example.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreationNewPostTest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        //chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://www.livejournal.com");
        WebElement btnLogin = driver.findElement(By.cssSelector(".s-header-item__link--login"));
        WebElement inputUsername = driver.findElement(By.cssSelector("#user"));
        WebElement inputPassword = driver.findElement(By.cssSelector("#lj_loginwidget_password"));
        WebElement btnInput = driver.findElement(By.cssSelector(".b-loginform-btn--auth"));
        btnLogin.click();
        inputUsername.sendKeys("silin_aleksei");
        inputPassword.sendKeys("1234As1234");
        btnInput.click();

        WebElement btnCreatePost = driver.findElement(By.xpath("//header/div[1]/nav[2]/ul/li[4]/a/span[1]"));
        btnCreatePost.click();
        WebElement inputTextPost = driver.findElement(By.cssSelector(".public-DraftStyleDefault-ltr"));
        inputTextPost.sendKeys("This is text of the post");
        WebElement inputPostTitle = driver.findElement(By.xpath("//*[@id='content']//div[2]/textarea"));
        inputPostTitle.sendKeys("My first post");
        WebElement btnConfigureAndPublish = driver.findElement(By.cssSelector(".backgroundBlue-0-2-104.textWithDropDownIcon-0-2-115"));
        btnConfigureAndPublish.click();
        WebElement btnPublish = driver.findElement(By.xpath("//footer/div/button/span"));
        //new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(btnPublish));
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//footer/div/button/span")));
        btnPublish.click();
        //driver.quit();

    }
}
