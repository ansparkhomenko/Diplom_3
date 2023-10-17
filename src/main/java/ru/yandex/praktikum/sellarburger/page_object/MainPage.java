package ru.yandex.praktikum.sellarburger.page_object;

import ru.yandex.praktikum.sellarburger.core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ru.yandex.praktikum.sellarburger.core.ProfilePage;

public class MainPage extends BasePage {
    //Кнопка "Личный кабинет"
    private By personalAccountButton = By.xpath("//*[text()='Личный Кабинет']");

    //Кнопка "Оформить заказ"
    private By makeAnOrderButton = By.xpath("//button[text()='Оформить заказ']");

    //----------------------------Конструктор-----------------------------------
    //Раздел "Начинки"
    private By filling = By.xpath("//span[text()='Начинки']/..");

    //Раздел "Соусы"
    private By sauces = By.xpath("//span[text()='Соусы']/..");

    //Раздел "Булки"
    private By buns = By.xpath("//span[text()='Булки']/..");

    //---------------------------CONSTANTS--------------------------------
    private static final String BUN_SECTION_TEXT = "Булки";
    private static final String FILLING_SECTION_TEXT = "Начинки";
    private static final String SAUCES_SECTION_TEXT = "Соусы";


    @Step("Нажатие на кнопку 'Личный кабинет' на главной странице для авторизованного пользователя")
    public ProfilePage clickPersonalAccountButtonForAuthorizedUser() {
        driver.findElement(personalAccountButton).click();
        return new ProfilePage();
    }
    @Step("Нажатие на кнопку 'Личный кабинет' на главной странице для неавторизованного пользователя")
    public LoginPage clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
        return new LoginPage();
    }

    @Step("Проверка видимости кнопки 'Оформить заказ' после авторизации")
    public boolean checkMakeAnOrderButtonIsDisplayed() {
        return driver.findElement(makeAnOrderButton).isDisplayed();
    }

    @Step("Переход в аккаунт по кнопке Войти в аккаунт")
    public LoginPage clickEnterAccountButton() {
        driver.findElement(personalAccountButton).click();
        return new LoginPage();
    }

    @Step("Клик по секции булочками")
    public void clickBunSection() {
        driver.findElement(buns).click();
    }

    @Step("Клик по секции с начинками")
    public void clickFillingSection() {
        driver.findElement(filling).click();
    }

    @Step("Клик по секции с соусами")
    public void clickSaucesSection() {
        driver.findElement(sauces).click();
    }

    @Step("Проверка выбора категории 'Булки'")
    public boolean bunIsSelected() {
        String x = driver.findElement(buns).getText();
        return x.contains(BUN_SECTION_TEXT);
    }

    @Step("Проверка выбора категории 'Начинки'")
    public boolean fillingIsSelected() {
        String x = driver.findElement(filling).getText();
        return x.contains(FILLING_SECTION_TEXT);
    }

    @Step("Проверка выбора категории 'Соусы'")
    public boolean saucesIsSelected() {
        String x = driver.findElement(sauces).getText();
        return x.contains(SAUCES_SECTION_TEXT);
    }
}
