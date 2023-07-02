package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RestorePasswordPage {

    private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    private final By fieldEmail    = By.xpath("//label[text()='Email']");

    private final By restoreButton = By.xpath("//button[text()='Восстановить']");

    private final By loginButton   = By.xpath("//a[text()='Войти']");

    private final WebDriver driver;

    public RestorePasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public RestorePasswordPage open() {
        driver.get(PAGE_URL);
        return this;
    }

    public void loginButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void restoreButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(restoreButton)).click();
    }

    public void  sendFieldEmail(String email) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(fieldEmail)).sendKeys(email);
    }

}
