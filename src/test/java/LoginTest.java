import clients.UserClient;
import io.qameta.allure.junit4.DisplayName;
import model.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pojo.CreateUser;

import java.time.Duration;

import static constants.constants.browserType;
import static constants.constants.profilePageText;

public class LoginTest {

    private final UserClient userClient = new UserClient();
    private final String email = RandomStringUtils.randomAlphabetic(8) + "@yandex.ru";
    private final String password = RandomStringUtils.randomAlphabetic(8);
    private WebDriver driver;
    private String accessToken;
    private MainPage mainPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;

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
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);

        CreateUser createUser = new CreateUser();
        createUser.setEmail(email);
        createUser.setName(RandomStringUtils.randomAlphabetic(8));
        createUser.setPassword(password);

        accessToken = userClient.create(createUser)
                .statusCode(200)
                .body("success", Matchers.equalTo(true))
                .extract().jsonPath().get("accessToken");

        mainPage.open();
        mainPage.waitMain();
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginButtonLoginTest() {
        String result;
        mainPage.loginButtonClick();
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
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void profileButtonLoginTest() {
        String result;
        mainPage.profileButtonClick();
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
    @DisplayName("Вход через кнопку в форме регистрации")
    public void registerPageLoginTest() {
        String result;
        RegisterPage registerPage = new RegisterPage(driver);
        mainPage.loginButtonClick();
        loginPage.waitLoginPage();
        loginPage.registerButtonClick();
        registerPage.waitRegisterPage();
        registerPage.loginButtonClick();
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
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void restorePasswordPageLoginTest() {
        String result;
        RestorePasswordPage restorePasswordPage = new RestorePasswordPage(driver);
        mainPage.loginButtonClick();
        loginPage.waitLoginPage();
        loginPage.restorePasswordButtonClick();
        restorePasswordPage.waitRestorePasswordPage();
        restorePasswordPage.loginButtonClick();
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

    @After
    public void tearDown() {
        driver.quit();

        if (accessToken != null) {
            userClient.delete(accessToken);
        }
    }

}
