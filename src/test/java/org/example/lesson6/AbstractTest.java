package org.example.lesson6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class AbstractTest {
    private static WebDriver webDriver;

    @BeforeAll
    static void init() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        //options.addArguments("--headless");
        options.addArguments("--start-maximized");
        options.setPageLoadTimeout(Duration.ofSeconds(20));

        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        //driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
    }

    @BeforeEach
    void initMainPage() {
        Assertions.assertDoesNotThrow(() -> getWebDriver().navigate()
                .to("https://www.livejournal.com"), "Страница не доступна");
    }

    @AfterEach
    public void close() {
        LoginPage loginPage = new LoginPage(getWebDriver());
        loginPage.logOut();
    }

    @AfterAll
    public static void exit() {
        if (webDriver != null) webDriver.quit();
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }
}
