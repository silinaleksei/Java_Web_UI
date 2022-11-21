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

public class DeletingPostTest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.livejournal.com");
        WebElement btnLogin = driver.findElement(By.cssSelector(".s-header-item__link--login"));
        WebElement inputUsername = driver.findElement(By.cssSelector("#user"));
        WebElement inputPassword = driver.findElement(By.cssSelector("#lj_loginwidget_password"));
        WebElement btnInput = driver.findElement(By.cssSelector(".b-loginform-btn--auth"));
        btnLogin.click();
        inputUsername.sendKeys("silin_aleksei");
        inputPassword.sendKeys("1234As1234");
        btnInput.click();

        WebElement btnUser = driver.findElement(By.cssSelector(".s-nav-item__name "));
        btnUser.click();
        WebElement post = driver.findElement(By.xpath("//a[text()='My first post']"));
        post.click();
        WebElement btnMenu = driver.findElement(By.cssSelector(".svgicon--more"));
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(btnMenu));
        btnMenu.click();
        WebElement btnEdit = driver.findElement(By.xpath("//span[contains(.,'Редактировать запись')]"));
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(btnEdit));
        btnEdit.click();
        WebElement btnDeletePost = driver.findElement(By.xpath("//footer/div/nav/a[1]"));
        btnDeletePost.click();
        WebElement btnDelete = driver.findElement(By.xpath("//button[2]/span"));
        btnDelete.click();
        btnUser.click();
        //driver.quit();

    }
}
