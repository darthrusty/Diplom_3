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
public class GoToMainPageTest {

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
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void constructorButtonTest() {
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
        profilePage.waitProfile();
        mainPage.constructorButtonClick();
        result = mainPage.getMain();
        Assert.assertTrue(result);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип")
    public void logoTest() {
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
        profilePage.waitProfile();
        mainPage.logoClick();
        result = mainPage.getMain();
        Assert.assertTrue(result);
    }

    @After
    public void tearDown() {
        driver.quit();

        if (accessToken != null) {
            userClient.delete(accessToken);
        }
    }

}
