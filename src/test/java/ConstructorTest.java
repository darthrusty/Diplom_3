import io.qameta.allure.junit4.DisplayName;
import model.MainPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static constants.constants.*;

public class ConstructorTest {

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void createUser() {
        ChromeOptions options = new ChromeOptions();
        switch (browserType) {
            case "Yandex": {
                System.setProperty("webdriver.chrome.driver", "c:/WebDriver/bin/yandexdriver.exe");
                break;
            }
            case "Chrome": {
                break;
            }
        }
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.waitMain();
    }

    @Test
    @DisplayName("Переход к разделу конструктора «Булки»")
    public void bunButtonClickTest() {
        String result;
        mainPage.saucesButtonClick();
        mainPage.bunButtonClick();
        mainPage.checkActiveMenu(bun);
        result = mainPage.getActiveMenu();
        Assert.assertEquals(bun, result);
    }

    @Test
    @DisplayName("Переход к разделу конструктора «Соусы»")
    public void saucesButtonClickTest() {
        String result;
        mainPage.saucesButtonClick();
        mainPage.checkActiveMenu(sauces);
        result = mainPage.getActiveMenu();
        Assert.assertEquals(sauces, result);
    }

    @Test
    @DisplayName("Переход к разделу конструктора «Начинки»")
    public void fillingsButtonClickTest() {
        String result;
        mainPage.fillingsButtonClick();
        mainPage.checkActiveMenu(fillings);
        result = mainPage.getActiveMenu();
        Assert.assertEquals(fillings, result);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
