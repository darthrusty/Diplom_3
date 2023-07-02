package model;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    private final By fieldEmail            = By.xpath("//label[text()='Email']/..//input[@type='text']");

    private final By fieldPassword         = By.xpath("//label[text()='Пароль']/..//input[@type='password']");

    private final By loginButton           = By.xpath("//button[text()='Войти']");

    private final By registerButton        = By.xpath("//a[text()='Зарегистрироваться']");

    private final By restorePasswordButton = By.xpath("//a[text()='Восстановить пароль']");

    private final By fieldEntrance         = By.xpath("//h2[text()='Вход']");

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage open() {
        driver.get(PAGE_URL);
        return this;
    }

    public void loginButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void registerButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    public void restorePasswordButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(restorePasswordButton)).click();
    }

    public void sendFieldEmail(String email) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(fieldEmail)).sendKeys(email);
    }

    public void sendFieldPassword(String password) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(fieldPassword)).sendKeys(password);
    }

    public boolean checkFieldEntrance() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(fieldEntrance));
        String text = driver.findElement(fieldEntrance).getText();
        return text.equals("Вход");
    }

    public void waitFieldEntrance() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(fieldEntrance));
    }

}
