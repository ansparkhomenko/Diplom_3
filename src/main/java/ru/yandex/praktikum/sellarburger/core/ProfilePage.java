package ru.yandex.praktikum.sellarburger.core;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.sellarburger.page_object.LoginPage;
import ru.yandex.praktikum.sellarburger.page_object.MainPage;

public class ProfilePage extends BasePage {
    //Кнопка "Конструктор" в верхней части страницы
    private By constructorButton = By.xpath("//p[text()='Конструктор']");

    //Логотип страницы
    private By logo = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']/a");

    //Кнопка "Выход"
    private By exitButton = By.xpath("//li[@class='Account_listItem__35dAP']/button");

    @Step("Нажатие на кнопку Конструктор")
    public MainPage clickConstructorButton() {
        driver.findElement(constructorButton).click();
        return new MainPage();
    }

    @Step("Нажатие на логотип")
    public MainPage clickOnLogo() {
        driver.findElement(logo).click();
        return new MainPage();
    }
    @Step("Нажатие на кнопку Выход")
    public LoginPage logOutButtonClick(){
        driver.findElement(exitButton).click();
        new WebDriverWait(driver, 3);
        return new LoginPage();
    }
}
