package model;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    private final By fieldEmail = By.xpath("//label[text()='Email']/..//input[@type='text']");

    private final By fieldPassword = By.xpath("//label[text()='Пароль']/..//input[@type='password']");

    private final By loginButton = By.xpath("//button[text()='Войти']");

    private final By registerButton = By.xpath("//a[text()='Зарегистрироваться']");

    private final By restorePasswordButton = By.xpath("//a[text()='Восстановить пароль']");

    private final By loginPageInfo = By.xpath("//h2[text()='Вход']");

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage open() {
        driver.get(PAGE_URL);
        return this;
    }

    @Step("Нажатие на Войти")
    public void loginButtonClick() {
        driver.findElement(loginButton).click();
    }

    @Step("Нажатие на Зарегистрироваться")
    public void registerButtonClick() {
        driver.findElement(registerButton).click();
    }

    @Step("Нажатие на Восстановить пароль")
    public void restorePasswordButtonClick() {
        driver.findElement(restorePasswordButton).click();
    }

    @Step("Ввод почты")
    public void sendFieldEmail(String email) {
        driver.findElement(fieldEmail).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void sendFieldPassword(String password) {
        driver.findElement(fieldPassword).sendKeys(password);
    }

    @Step("Подтверждение перехода на вкладку Вход")
    public String checkFieldEntrance() {
        return driver.findElement(loginPageInfo).getText();
    }

    @Step("Ожидание перехода на вкладку Вход")
    public void waitLoginPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(loginPageInfo));
    }

}
