package ru.yandex.praktikum.sellarburger;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.yandex.praktikum.sellarburger.core.BaseTest;
import ru.yandex.praktikum.sellarburger.core.ProfilePage;
import ru.yandex.praktikum.sellarburger.page_object.LoginPage;
import ru.yandex.praktikum.sellarburger.page_object.MainPage;
import ru.yandex.praktikum.sellarburger.user.UserServiceApi;

public class StellarBurgerNavigationTest extends BaseTest {
    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();
    ProfilePage profilePage = new ProfilePage();
    UserServiceApi userServiceApi = new UserServiceApi();
    private final static String EXPECTED_LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";
    private final static String EXPECTED_MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    @Test
    @DisplayName("Переход в личный кабинет")
    @Description("Переход по клику на «Личный кабинет»")
    public void moveToPersonalAccount() {
        mainPage.clickPersonalAccountButton();
        Assert.assertEquals("Неправильная ссылка", EXPECTED_LOGIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    @Description("Переход по клику на кнопку «Конструктор» в хедере страницы")
    public void moveToConstructorByButton() {
        userServiceApi.registerUser(user);
        mainPage.clickPersonalAccountButton()
                .loginUser(user.getEmail(), user.getPassword())
                .clickPersonalAccountButtonForAuthorizedUser()
                .clickConstructorButton();
        Assert.assertEquals("Неправильная ссылка", EXPECTED_MAIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по логотипу ")
    @Description("Переход по клику на логотип Stellar Burgers")
    public void moveToConstructorByLogo() {
        userServiceApi.registerUser(user);
        mainPage.clickPersonalAccountButton()
                .loginUser(user.getEmail(), user.getPassword())
                .clickPersonalAccountButtonForAuthorizedUser()
                .clickOnLogo();
        Assert.assertEquals("Неправильная ссылка", EXPECTED_MAIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Выход из личного кабинета")
    @Description("Выход по кнопке «Выйти» в личном кабинете")
    public void logOut() {
        userServiceApi.registerUser(user);
        mainPage.clickPersonalAccountButton()
                .loginUser(user.getEmail(), user.getPassword())
                .clickPersonalAccountButtonForAuthorizedUser().logOutButtonClick();
        Assert.assertTrue("Кнопка не отображается",loginPage.isRegButtonDisplayed());
    }
}
