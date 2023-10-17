package ru.yandex.praktikum.sellarburger;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.sellarburger.core.BaseTest;
import ru.yandex.praktikum.sellarburger.page_object.MainPage;

public class StellarBurgerConstructorTest extends BaseTest {
    MainPage mainPage = new MainPage();

    @Test
    @DisplayName("Проверка перехода к разделу 'Булки'")
    @Description("Выбор раздела конструктора с булками")
    public void checkBunSectionIsSelected() {
        mainPage.clickSaucesSection();
        new WebDriverWait(driver,2);
        mainPage.clickBunSection();
        boolean actual = mainPage.bunIsSelected();
        Assert.assertTrue("Раздел не выбран", actual);
    }

    @Test
    @DisplayName("Проверка перехода к разделу 'Соусы'")
    @Description("Выбор раздела конструктора с соусами")
    public void checkSaucesSectionIsSelected() {
        mainPage.clickSaucesSection();
        boolean actual = mainPage.saucesIsSelected();
        Assert.assertTrue("Раздел не выбран", actual);
    }

    @Test
    @DisplayName("Проверка перехода к разделу 'Начинки'")
    @Description("Выбор раздела конструктора с начинками")
    public void checkFillingSectionIsSelected() {
        mainPage.clickSaucesSection();
        mainPage.clickFillingSection();
        boolean actual = mainPage.fillingIsSelected();
        Assert.assertTrue("Раздел не выбран", actual);
    }
}
