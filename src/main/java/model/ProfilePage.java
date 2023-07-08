package model;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {

    private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    private final By profileButton = By.xpath("//a[text()='Профиль']");

    private final By historyButton = By.xpath("//a[text()='История заказов']");

    private final By logoutButton = By.xpath("//button[text()='Выход']");

    private final By fieldName = By.xpath("//input[@name='Name']");

    private final By fieldLogin = By.xpath("//input[@type='text' and @name='name']");

    private final By fieldPassword = By.xpath("//input[@type='password']");

    private final By saveButton = By.xpath("//button[text()='Сохранить']");

    private final By cancelButton = By.xpath("//button[text()='Отмена']");

    private final By profilePageInfo = By.xpath("//p[text()='В этом разделе вы можете изменить свои персональные данные']");

    private final WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public ProfilePage open() {
        driver.get(PAGE_URL);
        return this;
    }

    @Step("Нажатие кнопки Профиль")
    public void profileButtonClick() {
        driver.findElement(profileButton).click();
    }

    @Step("Нажатие кнопки История заказов")
    public void historyButtonClick() {
        driver.findElement(historyButton).click();
    }

    @Step("Нажатие кнопки Выход")
    public void logoutButtonClick() {
        driver.findElement(logoutButton).click();
    }

    @Step("Нажатие кнопки Сохранить")
    public void saveButtonClick() {
        driver.findElement(saveButton).click();
    }

    @Step("Нажатие кнопки Отмена")
    public void cancelButtonClick() {
        driver.findElement(cancelButton).click();
    }

    @Step("Ввод имени")
    public void sendFieldName(String name) {
        driver.findElement(fieldName).sendKeys(name);
    }

    @Step("Ввод Логина")
    public void sendFieldLogin(String login) {
        driver.findElement(fieldLogin).sendKeys(login);
    }

    @Step("Ввод пароля")
    public void sendFieldPassword(String password) {
        driver.findElement(fieldPassword).sendKeys(password);
    }

    @Step("Ожидание перехода на страницу профиля")
    public void waitProfile() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(profilePageInfo));
    }

    @Step("Подтверждение перехода на страницу профиля")
    public String checkProfile() {
        return driver.findElement(profilePageInfo).getText();
    }

}
