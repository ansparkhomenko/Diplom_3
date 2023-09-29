package ru.yandex.praktikum.sellarburger.page_object;

import ru.yandex.praktikum.sellarburger.core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    //Поле для ввода Email
    private By emailField = By.xpath("//label[text()='Email']/../input");
    //Поле для ввода пароля
    private By passwordField = By.xpath("//label[text()='Пароль']/../input");
    //Кнопка "Войти"
    private By loginButton = By.xpath("//button[text()='Войти']");
    //Кнопка "Зарегистрироваться"
    private By registerButton = By.xpath("//a[text()='Зарегистрироваться']");
    private By restorePasswordButton = By.xpath("//a[@class='Auth_link__1fOlj' and text()='Восстановить пароль']");


    @Step("Клик по ссылке 'Зарегистрироваться'")
    public RegisterPage clickRegisterButton() {
        driver.findElement(registerButton).click();
        return new RegisterPage();
    }

    @Step("Установить значение в поле Email")
    public void enterEmailFieldValue(String email) {
        driver.findElement(emailField).click();
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Установить значение в поле Пароль")
    public void enterPasswordFieldValue(String pass) {
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(pass);
    }

    @Step("Авторизация пользователя")
    public MainPage loginUser(String email, String pass) {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        enterEmailFieldValue(email);
        enterPasswordFieldValue(pass);
        clickLoginButton();
        return new MainPage();
    }


    @Step("Нажать кнопку Войти")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Нажатие на кнопку 'Восстановить пароль'")
    public RestorePasswordPage clickRestoreButton() {
        driver.findElement(restorePasswordButton).click();
        return new RestorePasswordPage();
    }
    @Step("Проверка видимости кнопки Зарегистрироваться")
    public boolean isRegButtonDisplayed(){
        return driver.findElement(registerButton).isDisplayed();
    }
}
