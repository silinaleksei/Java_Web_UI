package org.example.lesson6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.time.Duration;
import java.util.List;

public class AbstractTest {
    //private static WebDriver webDriver;
    static EventFiringWebDriver eventDriver;


    @BeforeAll
    static void init() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        //options.addArguments("--headless");
        options.addArguments("--start-maximized");
        options.setPageLoadTimeout(Duration.ofSeconds(20));

        //webDriver = new ChromeDriver(options);
        //webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        //driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        eventDriver = new EventFiringWebDriver(new ChromeDriver(options));
        eventDriver.register(new MyWebDriverEventListener());

        eventDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
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

    @AfterEach
    public void checkBrowser() {
        List<LogEntry> allLogRows = getWebDriver().manage().logs().get(LogType.BROWSER).getAll();
        if (!allLogRows.isEmpty()) {
            if (allLogRows.size() > 0) {
                allLogRows.forEach(logEntry -> {
                    System.out.println(logEntry.getMessage());
                });
            }
        }
    }

//    @AfterAll
//    public static void exit() {
//        if (webDriver != null) webDriver.quit();
//    }

    @AfterAll
    public static void exit() {
        if (eventDriver != null) eventDriver.quit();
    }

    //    public WebDriver getWebDriver() {
//        return webDriver;
//    }
    public WebDriver getWebDriver() {
        return this.eventDriver;
    }

}
