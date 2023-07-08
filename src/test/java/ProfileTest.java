import clients.UserClient;
import io.qameta.allure.junit4.DisplayName;
import model.LoginPage;
import model.MainPage;
import model.ProfilePage;
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

public class ProfileTest {

    private final UserClient userClient = new UserClient();
    private final String email = RandomStringUtils.randomAlphabetic(8) + "@yandex.ru";
    private final String password = RandomStringUtils.randomAlphabetic(8);
    private WebDriver driver;
    private String accessToken;

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
    @DisplayName("Переход по клику на «Личный кабинет»")
    public void goToProfileTest() {
        String result;
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        mainPage.open();
        mainPage.waitMain();
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

    @After
    public void tearDown() {
        driver.quit();

        if (accessToken != null) {
            userClient.delete(accessToken);
        }
    }

}
