package ru.yandex.praktikum.sellarburger.page_object;

import ru.yandex.praktikum.sellarburger.core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class RegisterPage extends BasePage {

    //Поле для ввода имени пользователя
    private By nameField = By.xpath("//label[text()='Имя']/../input");

    //Поле для ввода email пользователя
    private By emailField = By.xpath("//label[text()='Email']/../input");

    //Поле для ввода пароля пользователя
    private By passwordField = By.xpath("//label[text()='Пароль']/../input");

    //Кнопка "Зарегистрироваться"
    private By registerButton = By.cssSelector(".button_button__33qZ0");

    //Ошибка при вводе пароля < 6 символов
    private By incorrectPasswordError = By.xpath("//p[text()='Некорректный пароль']");

    //Кнопка "Войти"
    private By loginButton = By.xpath("//a[@href='/login']");

    @Step("Регистрация пользователя")
    public LoginPage registerUser(String name, String email, String pass) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(pass);
        driver.findElement(registerButton).click();
        return new LoginPage();
    }

    @Step("Получение текста ошибки")
    public String getErrorText() {
        return driver.findElement(incorrectPasswordError).getText();
    }

    @Step("Нажатие на кнопку 'Войти'")
    public LoginPage clickLogin() {
         driver.findElement(loginButton).click();
        return new LoginPage();
    }
}
