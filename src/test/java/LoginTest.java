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

public class LoginTest {

    private WebDriver driver;
    private UserClient userClient = new UserClient();

    private String accessToken;
    private String email    = RandomStringUtils.randomAlphabetic(8) + "@yandex.ru";
    private String password = RandomStringUtils.randomAlphabetic(8);

    @Before
    public void createUser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        CreateUser createUser = new CreateUser();
        createUser.setEmail(email);
        createUser.setName(RandomStringUtils.randomAlphabetic(8));
        createUser.setPassword(password);

        accessToken = userClient.create(createUser)
                .statusCode(200)
                .body("success", Matchers.equalTo(true))
                .extract().jsonPath().get("accessToken");
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginButtonLoginTest() {
        boolean result;
        MainPage    mainPage    = new MainPage(driver);
        LoginPage   loginPage   = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        mainPage.open();
        mainPage.loginButtonClick();
        loginPage.sendFieldEmail(email);
        loginPage.sendFieldPassword(password);
        loginPage.loginButtonClick();
        mainPage.waitMain();
        mainPage.profileButtonClick();
        result = profilePage.checkProfile();
        Assert.assertTrue(result);
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void profileButtonLoginTest() {
        boolean result;
        MainPage    mainPage    = new MainPage(driver);
        LoginPage   loginPage   = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        mainPage.open();
        mainPage.profileButtonClick();
        loginPage.sendFieldEmail(email);
        loginPage.sendFieldPassword(password);
        loginPage.loginButtonClick();
        mainPage.waitMain();
        mainPage.profileButtonClick();
        result = profilePage.checkProfile();
        Assert.assertTrue(result);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void registerPageLoginTest() {
        boolean result;
        MainPage     mainPage     = new MainPage(driver);
        LoginPage    loginPage    = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        ProfilePage  profilePage  = new ProfilePage(driver);
        mainPage.open();
        mainPage.loginButtonClick();
        loginPage.registerButtonClick();
        registerPage.loginButtonClick();
        loginPage.sendFieldEmail(email);
        loginPage.sendFieldPassword(password);
        loginPage.loginButtonClick();
        mainPage.waitMain();
        mainPage.profileButtonClick();
        result = profilePage.checkProfile();
        Assert.assertTrue(result);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void restorePasswordPageLoginTest() {
        MainPage            mainPage            = new MainPage(driver);
        LoginPage           loginPage           = new LoginPage(driver);
        ProfilePage         profilePage         = new ProfilePage(driver);
        RestorePasswordPage restorePasswordPage = new RestorePasswordPage(driver);
        mainPage.open();
        mainPage.loginButtonClick();
        loginPage.restorePasswordButtonClick();
        restorePasswordPage.loginButtonClick();
        loginPage.sendFieldEmail(email);
        loginPage.sendFieldPassword(password);
        loginPage.loginButtonClick();
        mainPage.waitMain();
        mainPage.profileButtonClick();
        profilePage.waitProfile();
    }

    @After
    public void tearDown() {
        driver.quit();

        if (accessToken != null) {
            userClient.delete(accessToken);
        }
    }

}
