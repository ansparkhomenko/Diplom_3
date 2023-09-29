package ru.yandex.praktikum.sellarburger.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.sellarburger.user.UserData;
import ru.yandex.praktikum.sellarburger.user.UserServiceApi;


import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    protected UserData user;
    protected UserServiceApi userServiceApi = new UserServiceApi();
    protected Response response;



    @Before
    public void setUp() {
        //Для запуска в других браузерах надо указать BrowserType.FIREFOX или BrowserType.YANDEX в методе getDriver()
        driver = getDriver(BrowserType.CHROME);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        BasePage.setDriver(driver);
        driver.get(BASE_URL);
        //Создание уникального пользователя
        String uniqueEmail = UserHelper.generateUniqueEmail();
        user = new UserData(uniqueEmail, "qwe123", "Bob");

    }

    public static WebDriver getDriver(BrowserType browserType) {
        switch (browserType) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case YANDEX:
                WebDriverManager.chromiumdriver().setup();
                System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe");
                return new ChromeDriver();
            default:
                throw new IllegalArgumentException("Неподдерживаемый браузер: " + browserType);
        }
    }

    @After
    public void tearDown() {
        response = userServiceApi.loginUser(user);
        String accessToken = response.then().extract().path("accessToken");

        if (accessToken != null){
            userServiceApi.deleteUser(accessToken);
        }
        driver.quit();
    }
}
