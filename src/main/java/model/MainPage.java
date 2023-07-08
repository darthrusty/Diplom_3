package model;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/";


    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");

    private final By profileButton = By.xpath("//p[text()='Личный Кабинет']");

    private final By constructorButton = By.xpath("//p[text()='Конструктор']");

    private final By bunButton = By.xpath("//span[text()='Булки']");

    private final By saucesButton = By.xpath("//span[text()='Соусы']");

    private final By fillingsButton = By.xpath("//span[text()='Начинки']");

    private final By logo = By.xpath("//div[contains(@class, 'AppHeader_header')]");

    private final By mainPageInfo = By.xpath("//h1[text()='Соберите бургер']");
    private final By activeMenu = By.xpath("//div[contains(@class, 'tab_tab_type_current')]/span[contains(@class, 'text_type_main-default')]");

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage open() {
        driver.get(PAGE_URL);
        return this;
    }

    @Step("Нажатие кнопки Войти в аккаунт")
    public void loginButtonClick() {
        driver.findElement(loginButton).click();
    }

    @Step("Нажатие кнопки Личный Кабинет")
    public void profileButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(profileButton)).click();
    }

    @Step("Нажатие кнопки Конструктор")
    public void constructorButtonClick() {
        driver.findElement(constructorButton).click();
    }

    @Step("Нажатие кнопки Булки")
    public void bunButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(bunButton)).click();
    }

    @Step("Нажатие кнопки Соусы")
    public void saucesButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(saucesButton)).click();
    }

    @Step("Нажатие кнопки Начинки")
    public void fillingsButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(fillingsButton)).click();
    }

    @Step("Нажатие на Логотип")
    public void logoClick() {
        driver.findElement(logo).click();
    }

    @Step("Ожидание перехода на главную страницу")
    public void waitMain() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(mainPageInfo));
    }

    @Step("Подтверждение перехода на главную страницу")
    public String getMain() {
        return driver.findElement(mainPageInfo).getText();
    }

    @Step("Ожидание перехода на заданную активную вкладку")
    public void checkActiveMenu(String menu) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'tab_tab_type_current')]/span[contains(@class, 'text_type_main-default') and (text()='" + menu + "')]")));
    }

    @Step("Выдача активной вкладки")
    public String getActiveMenu() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(activeMenu));
        return driver.findElement(activeMenu).getText();
    }
}
