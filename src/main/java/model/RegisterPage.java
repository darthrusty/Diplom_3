package model;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

    private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    private final By fieldName      = By.xpath("//label[text()='Имя']/..//input[@type='text']");

    private final By fieldEmail     = By.xpath("//label[text()='Email']/..//input[@type='text']");

    private final By fieldPassword  = By.xpath("//label[text()='Пароль']/..//input[@type='password']");

    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");

    private final By loginButton    = By.xpath("//a[text()='Войти']");

    private final By errorPassword  = By.xpath("//p[text()='Некорректный пароль']");

    private final WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public RegisterPage open() {
        driver.get(PAGE_URL);
        return this;
    }

    public void loginButtonClick() {
        driver.findElement(loginButton).click();
    }

    public void registerButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    public void  sendFieldName(String name) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(fieldName)).sendKeys(name);
    }

    public void  sendFieldEmail(String email) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(fieldEmail)).sendKeys(email);
    }

    public void  sendFieldPassword(String password) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(fieldPassword)).sendKeys(password);
    }

    public boolean  checkErrorPasswordMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(errorPassword));
        String text = driver.findElement(errorPassword).getText();
        return text.equals("Некорректный пароль");
    }

}
