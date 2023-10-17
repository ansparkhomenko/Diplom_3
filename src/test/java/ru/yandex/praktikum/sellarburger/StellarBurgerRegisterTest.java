package ru.yandex.praktikum.sellarburger;

import ru.yandex.praktikum.sellarburger.core.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.yandex.praktikum.sellarburger.page_object.MainPage;
import ru.yandex.praktikum.sellarburger.page_object.RegisterPage;

public class StellarBurgerRegisterTest extends BaseTest {

    private MainPage mainPage = new MainPage();
    private RegisterPage registerPage = new RegisterPage();


    @org.junit.Test
    @DisplayName("Успешная регистрация")
    @Description("Регистрация пользователя и вход в аккаунт")
    public void successUserRegisterTest() {
        mainPage.clickPersonalAccountButton()
                .clickRegisterButton()
                .registerUser(user.getName(), user.getEmail(), user.getPassword())
                .loginUser(user.getEmail(), user.getPassword());
        Assert.assertTrue(mainPage.checkMakeAnOrderButtonIsDisplayed());
    }

    @Test
    @DisplayName("Неуспешная регистрация. Ошибка некорректного пароля")
    @Description("Попытка регистрации пользователя с паролем меньше 6 символов. Проверка отображения ошибки")
    public void checkPasswordError() {
        user.setPassword("qw12");
        mainPage.clickPersonalAccountButton()
                .clickRegisterButton()
                .registerUser(user.getName(), user.getEmail(), user.getPassword());
        String actual = registerPage.getErrorText();
        String expected = "Некорректный пароль";
        Assert.assertEquals("Неправильный текст ошибки",expected, actual);
    }
}
