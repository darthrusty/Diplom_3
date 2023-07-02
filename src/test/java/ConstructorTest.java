import io.qameta.allure.junit4.DisplayName;
import model.MainPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ConstructorTest {

    private WebDriver driver;

    private final String bun      = "Булки";
    private final String sauces   = "Соусы";
    private final String fillings = "Начинки";

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @Test
    @DisplayName("Переход к разделу конструктора «Булки»")
    public void bunButtonClickTest() {
        String  text;
        Boolean result;
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.saucesButtonClick();
        mainPage.bunButtonClick();
        mainPage.checkActiveMenu(bun);
        text   = mainPage.getActiveMenu();
        result = text.equals(bun);
        Assert.assertTrue(result);
    }

    @Test
    @DisplayName("Переход к разделу конструктора «Соусы»")
    public void saucesButtonClickTest() {
        String  text;
        Boolean result;
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.saucesButtonClick();
        mainPage.checkActiveMenu(sauces);
        text   = mainPage.getActiveMenu();
        result = text.equals(sauces);
        Assert.assertTrue(result);
    }

    @Test
    @DisplayName("Переход к разделу конструктора «Начинки»")
    public void fillingsButtonClickTest() {
        String  text;
        Boolean result;
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.fillingsButtonClick();
        mainPage.checkActiveMenu(fillings);
        text   = mainPage.getActiveMenu();
        result = text.equals(fillings);
        Assert.assertTrue(result);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
