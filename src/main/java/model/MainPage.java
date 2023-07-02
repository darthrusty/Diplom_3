package model;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/";


    private final By loginButton       = By.xpath("//button[text()='Войти в аккаунт']");

    private final By profileButton     = By.xpath("//p[text()='Личный Кабинет']");

    private final By constructorButton = By.xpath("//p[text()='Конструктор']");

    private final By bunButton         = By.xpath("//span[text()='Булки']");

    private final By saucesButton      = By.xpath("//span[text()='Соусы']");

    private final By fillingsButton    = By.xpath("//span[text()='Начинки']");

    private final By logo              = By.xpath("//div[contains(@class, 'AppHeader_header')]");

    private final By mainPageInfo      = By.xpath("//h1[text()='Соберите бургер']");
    private final By activeMenu        = By.xpath("//div[contains(@class, 'tab_tab_type_current')]/span[contains(@class, 'text_type_main-default')]");

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage open() {
        driver.get(PAGE_URL);
        return this;
    }

    public void loginButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void profileButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(profileButton)).click();
    }

    public void constructorButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(constructorButton)).click();
    }

    public void bunButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(bunButton)).click();
    }

    public void saucesButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(saucesButton)).click();
    }

    public void fillingsButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(fillingsButton)).click();
    }

    public void logoClick() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(logo)).click();
    }

    public void waitMain() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(mainPageInfo));
    }

    public boolean getMain() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(mainPageInfo));
        String text = driver.findElement(mainPageInfo).getText();
        return text.equals("Соберите бургер");
    }

    public void checkActiveMenu(String menu) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'tab_tab_type_current')]/span[contains(@class, 'text_type_main-default') and (text()='" + menu + "')]")));
    }

    public String getActiveMenu() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(activeMenu));
        return driver.findElement(activeMenu).getText();
    }
}
