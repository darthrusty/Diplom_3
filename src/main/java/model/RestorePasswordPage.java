package model;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RestorePasswordPage {

    private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    private final By fieldEmail = By.xpath("//label[text()='Email']");

    private final By restoreButton = By.xpath("//button[text()='Восстановить']");

    private final By loginButton = By.xpath("//a[text()='Войти']");

    private final By restorePasswordPageInfo = By.xpath("//h2[text()='Восстановление пароля']");

    private final WebDriver driver;

    public RestorePasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public RestorePasswordPage open() {
        driver.get(PAGE_URL);
        return this;
    }

    @Step("Нажатие кнопки Войти")
    public void loginButtonClick() {
        driver.findElement(loginButton).click();
    }

    @Step("Нажатие кнопки Восстановить")
    public void restoreButtonClick() {
        driver.findElement(restoreButton).click();
    }

    @Step("Ввод почты")
    public void sendFieldEmail(String email) {
        driver.findElement(fieldEmail).sendKeys(email);
    }

    @Step("Ожидание перехода на страницу восстановление пароля")
    public void waitRestorePasswordPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(restorePasswordPageInfo));
    }

}
