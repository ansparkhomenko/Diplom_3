package ru.yandex.praktikum.sellarburger;

import ru.yandex.praktikum.sellarburger.core.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.yandex.praktikum.sellarburger.page_object.MainPage;
import ru.yandex.praktikum.sellarburger.user.UserServiceApi;

public class StellarBurgerLoginTest extends BaseTest {
    MainPage mainPage = new MainPage();
    private UserServiceApi userServiceApi = new UserServiceApi();

    @Test
    @DisplayName("Логин с главной страницы")
    @Description("Проверка входа по кнопке «Войти в аккаунт» на главной")
    public void loginFromMainPage() {
        userServiceApi.registerUser(user);
        mainPage.clickEnterAccountButton()
                .loginUser(user.getEmail(), user.getPassword());
        Assert.assertTrue("Кнопка 'Оформить заказ' не отображается", mainPage.checkMakeAnOrderButtonIsDisplayed());
    }

    @Test
    @DisplayName("Логин через личный кабинет")
    @Description("Проверка входа по кнопке «Личный кабинет» на главной")
    public void loginFromPersonalAccount() {
        userServiceApi.registerUser(user);
        mainPage.clickPersonalAccountButton()
                .loginUser(user.getEmail(), user.getPassword());
        Assert.assertTrue("Кнопка 'Оформить заказ' не отображается", mainPage.checkMakeAnOrderButtonIsDisplayed());
    }

    @Test
    @DisplayName("Логин через форму регистрации")
    @Description("Проверка входа по кнопке «Войти» на странице регистрации")
    public void loginFromRegisterPage() {
        userServiceApi.registerUser(user);
        mainPage.clickPersonalAccountButton()
                .clickRegisterButton()
                .clickLogin()
                .loginUser(user.getEmail(),user.getPassword());
        Assert.assertTrue("Кнопка 'Оформить заказ' не отображается", mainPage.checkMakeAnOrderButtonIsDisplayed());
    }

    @Test
    @DisplayName("Логин через форму восстановления пароля")
    @Description("Проверка входа по кнопке «Войти» на странице восстановления пароля")
    public void loginFromRestorePasswordPage(){
        userServiceApi.registerUser(user);
        mainPage.clickEnterAccountButton()
                .clickRestoreButton()
                .clickLoginButton()
                .loginUser(user.getEmail(), user.getPassword());
        Assert.assertTrue("Кнопка 'Оформить заказ' не отображается", mainPage.checkMakeAnOrderButtonIsDisplayed());
    }
}
