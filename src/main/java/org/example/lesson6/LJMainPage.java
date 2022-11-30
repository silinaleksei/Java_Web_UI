package org.example.lesson6;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Objects;
import java.util.Set;

public class LJMainPage extends AbstractPage{



    @FindBy(css = ".s-do-item-search-btn .svgicon")
    private WebElement btnSearch;

    @FindBy(css = ".s-inline-search-input")
    private WebElement inputSearch;

    public LJMainPage(WebDriver driver) {
        super(driver);
    }

    public void toSearch(String searchString) throws InterruptedException {
        btnSearch.click();
        inputSearch.sendKeys(searchString, Keys.ENTER);
        Thread.sleep(2000);
        String window1 = getDriver().getWindowHandle();
        Set<String> windowHandles = getDriver().getWindowHandles();
        String window2 = null;
        for (String window : windowHandles) {
            if (!Objects.equals(window, window1)) {
                window2 = window;
                break;
            }
        }
        getDriver().switchTo().window(window2);
        Thread.sleep(2000);
    }
}
