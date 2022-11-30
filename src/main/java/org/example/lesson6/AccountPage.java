package org.example.lesson6;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends AbstractPage{

    @FindBy(xpath = "//header/div[1]/nav[2]/ul/li[4]/a/span[1]")
    private WebElement btnCreatePost;

    @FindBy(css = ".public-DraftStyleDefault-ltr")
    private WebElement inputTextPost;

    @FindBy(xpath = "//*[@id='content']//div[2]/textarea")
    private WebElement inputPostTitle;

    @FindBy(css = ".backgroundBlue-0-2-104.textWithDropDownIcon-0-2-115")
    private WebElement btnConfigureAndPublish;

    @FindBy(xpath = "//footer/div/button/span")
    private WebElement btnPublish;

    @FindBy(xpath = "//a[text()='My first post']")
    private WebElement post;

    @FindBy(css = ".svgicon--more")
    private WebElement btnMenu;

    @FindBy(xpath = "//span[contains(.,'Редактировать запись')]")
    private WebElement btnEdit;

    @FindBy(xpath = "//footer/div/nav/a[1]")
    private WebElement btnDeletePost;

    @FindBy(xpath = "//button[2]/span")
    private WebElement btnDelete;

    public AccountPage(WebDriver driver) {
        super(driver);
    }
    public void createPost(String textPost, String titlePost) {
        btnCreatePost.click();
        inputTextPost.sendKeys(textPost);
        inputPostTitle.sendKeys(titlePost);
        btnConfigureAndPublish.click();
        btnPublish.click();
    }

    public void editPost() {
        post.click();
        btnMenu.click();
        btnEdit.click();
        inputTextPost.sendKeys(Keys.ENTER, "The text of the post is changed");
        btnConfigureAndPublish.click();
        btnPublish.click();
    }

    public void deletePost() {
        post.click();
        btnMenu.click();
        btnEdit.click();
        btnDeletePost.click();
        btnDelete.click();
    }
}
