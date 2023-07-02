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

public class RegisterTest {

    private WebDriver driver;
    private UserClient userClient = new UserClient();
    private String email         = RandomStringUtils.randomAlphabetic(8) + "@yandex.ru";
    private String name          = RandomStringUtils.randomAlphabetic(8);
    private String password      = RandomStringUtils.randomAlphabetic(8);
    private String shortPassword = RandomStringUtils.randomAlphabetic(5);

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @Test
    @DisplayName("Успешная регситрация")
    public void registerUserTest() {
        boolean result;
        MainPage     mainPage     = new MainPage(driver);
        LoginPage    loginPage    = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        ProfilePage  profilePage  = new ProfilePage(driver);
        mainPage.open();
        mainPage.loginButtonClick();
        loginPage.registerButtonClick();
        registerPage.sendFieldName(name);
        registerPage.sendFieldEmail(email);
        registerPage.sendFieldPassword(password);
        registerPage.registerButtonClick();
        loginPage.waitFieldEntrance();
        loginPage.sendFieldEmail(email);
        loginPage.sendFieldPassword(password);
        loginPage.loginButtonClick();
        mainPage.waitMain();
        mainPage.profileButtonClick();
        result = profilePage.checkProfile();
        Assert.assertTrue(result);
    }

    @Test
    @DisplayName("Ошибка регистрации для некорректного пароля. Минимальный пароль — шесть символов.")
    public void registerUserWithShortPasswordTest() {
        boolean result;
        MainPage     mainPage     = new MainPage(driver);
        LoginPage    loginPage    = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        mainPage.open();
        mainPage.loginButtonClick();
        loginPage.registerButtonClick();
        registerPage.sendFieldName(name);
        registerPage.sendFieldEmail(email);
        registerPage.sendFieldPassword(shortPassword);
        registerPage.registerButtonClick();
        result = registerPage.checkErrorPasswordMessage();
        Assert.assertTrue(result);
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
