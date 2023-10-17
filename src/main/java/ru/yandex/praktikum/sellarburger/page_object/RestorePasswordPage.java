package ru.yandex.praktikum.sellarburger.page_object;

import ru.yandex.praktikum.sellarburger.core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class RestorePasswordPage extends BasePage {

    private By loginButton = By.xpath("//a[@class='Auth_link__1fOlj']");

    @Step("Нажатие кнопки войти на странице 'Восстановление пароля'")
    public LoginPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return new LoginPage();
    }
}
