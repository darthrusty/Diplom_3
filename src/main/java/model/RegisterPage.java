package model;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    private final By fieldName = By.xpath("//label[text()='Имя']/..//input[@type='text']");

    private final By fieldEmail = By.xpath("//label[text()='Email']/..//input[@type='text']");

    private final By fieldPassword = By.xpath("//label[text()='Пароль']/..//input[@type='password']");

    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");

    private final By loginButton = By.xpath("//a[text()='Войти']");

    private final By errorPassword = By.xpath("//p[text()='Некорректный пароль']");

    private final By registerPageInfo = By.xpath("//h2[text()='Регистрация']");

    private final WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public RegisterPage open() {
        driver.get(PAGE_URL);
        return this;
    }

    @Step("Нажатие кнопки Войти")
    public void loginButtonClick() {
        driver.findElement(loginButton).click();
    }

    @Step("Нажатие кнопки Зарегистрироваться")
    public void registerButtonClick() {
        driver.findElement(registerButton).click();
    }

    @Step("Ввод имени")
    public void sendFieldName(String name) {
        driver.findElement(fieldName).sendKeys(name);
    }

    @Step("Ввод почты")
    public void sendFieldEmail(String email) {
        driver.findElement(fieldEmail).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void sendFieldPassword(String password) {
        driver.findElement(fieldPassword).sendKeys(password);
    }

    @Step("Получение сообщения о некорректном пароле")
    public String checkErrorPasswordMessage() {
        return driver.findElement(errorPassword).getText();
    }

    @Step("Ожидание перехода на страницу регистрации")
    public void waitRegisterPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(registerPageInfo));
    }

}
