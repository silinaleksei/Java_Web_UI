package org.example.lesson5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.Objects;
import java.util.Set;

public class SearchTest extends AbstractTest {
    @Test
    public void searchTest() throws InterruptedException {

        WebElement btnSearch = getDriver().findElement(By.cssSelector(".s-do-item-search-btn .svgicon"));
        btnSearch.click();
        WebElement inputSearch = getDriver().findElement(By.cssSelector(".s-inline-search-input"));
        inputSearch.sendKeys("New", Keys.ENTER);
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
        Assertions.assertEquals("LJ search", getDriver().getTitle());
        //Assertions.assertTrue(getDriver().findElement(By.cssSelector(".rsearch-found-info > .ng-binding")).getText().contains("Found"));
    }
}
