import clients.UserClient;
import io.qameta.allure.junit4.DisplayName;
import model.LoginPage;
import model.MainPage;
import model.ProfilePage;
import model.RegisterPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pojo.LoginUser;

import java.time.Duration;

import static constants.constants.*;

public class RegisterTest {

    private final UserClient userClient = new UserClient();
    private final String email = RandomStringUtils.randomAlphabetic(8) + "@yandex.ru";
    private final String name = RandomStringUtils.randomAlphabetic(8);
    private final String password = RandomStringUtils.randomAlphabetic(8);
    private final String shortPassword = RandomStringUtils.randomAlphabetic(5);
    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;

    @Before
    public void setUp() {
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
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        mainPage.open();
        mainPage.waitMain();
        mainPage.loginButtonClick();
        loginPage.waitLoginPage();
        loginPage.registerButtonClick();
        registerPage.waitRegisterPage();
        registerPage.sendFieldName(name);
        registerPage.sendFieldEmail(email);
    }

    @Test
    @DisplayName("Успешная регситрация")
    public void registerUserTest() {
        String result;
        ProfilePage profilePage = new ProfilePage(driver);
        registerPage.sendFieldPassword(password);
        registerPage.registerButtonClick();
        loginPage.waitLoginPage();
        loginPage.sendFieldEmail(email);
        loginPage.sendFieldPassword(password);
        loginPage.loginButtonClick();
        mainPage.waitMain();
        mainPage.profileButtonClick();
        profilePage.waitProfile();
        result = profilePage.checkProfile();
        Assert.assertEquals(profilePageText, result);
    }

    @Test
    @DisplayName("Ошибка регистрации для некорректного пароля. Минимальный пароль — шесть символов.")
    public void registerUserWithShortPasswordTest() {
        String result;
        registerPage.sendFieldPassword(shortPassword);
        registerPage.registerButtonClick();
        result = registerPage.checkErrorPasswordMessage();
        Assert.assertEquals(passwordErrorMessage, result);
    }

    @After
    public void tearDown() {
        driver.quit();

        String accessToken;

        LoginUser loginUser = new LoginUser();
        loginUser.setEmail(email);
        loginUser.setPassword(password);

        accessToken = userClient.login(loginUser)
                .extract().jsonPath().get("accessToken");

        if (accessToken != null) {
            userClient.delete(accessToken);
        }

    }

}
