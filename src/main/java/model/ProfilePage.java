package model;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage {

    private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    private final By profileButton   = By.xpath("//a[text()='Профиль']");

    private final By historyButton   = By.xpath("//a[text()='История заказов']");

    private final By logoutButton    = By.xpath("//button[text()='Выход']");

    private final By fieldName       = By.xpath("//input[@name='Name']");

    private final By fieldLogin      = By.xpath("//input[@type='text' and @name='name']");

    private final By fieldPassword   = By.xpath("//input[@type='password']");

    private final By saveButton      = By.xpath("//button[text()='Сохранить']");

    private final By cancelButton    = By.xpath("//button[text()='Отмена']");

    private final By profilePageInfo = By.xpath("//p[text()='В этом разделе вы можете изменить свои персональные данные']");

    private final WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public ProfilePage open() {
        driver.get(PAGE_URL);
        return this;
    }

    public void profileButtonClick() {
        driver.findElement(profileButton).click();
    }

    public void historyButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(historyButton)).click();
    }

    public void logoutButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    public void saveButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public void cancelButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(cancelButton)).click();
    }

    public void  sendFieldName(String name) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(fieldName)).sendKeys(name);
    }

    public void  sendFieldLogin(String login) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(fieldLogin)).sendKeys(login);
    }

    public void  sendFieldPassword(String password) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(fieldPassword)).sendKeys(password);
    }

    public void waitProfile() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(profilePageInfo));
    }

    public boolean checkProfile() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(profilePageInfo));
        String text = driver.findElement(profilePageInfo).getText();
        return text.equals("В этом разделе вы можете изменить свои персональные данные");
    }

}
