package yandexTest.steps;

import io.qameta.allure.Step;
import org.junit.Assert;
import yandexTest.pages.MarketPage;

import java.util.List;

import static yandexTest.core.PageManager.getCurrentPage;
import static yandexTest.core.PageManager.setupPage;


class AllSteps {

    private MarketPage marketPage = new MarketPage();

    @Step("Переходим на страницу - {0}")
    void openPage(String title) {
        setupPage(title);
    }

    @Step("Находим поле - {0} и вводим цену {1}")
    void setPrice(String nameField, String price) {
        scroll(nameField);
        sendKeys(nameField, price);
    }

    @Step("Выбираем бренды")
    void setBrands(String buttonName, List<String> listOfBrands) {
        scroll(buttonName);
        click(buttonName);
        for (String brand : listOfBrands) {
            loadElements("Поле поиска");
            sendKeys("Поле поиска", brand);
            Assert.assertEquals("Значение в поле задано неверно", brand, getCurrentPage()
                    .getElementByTitle("Поле поиска").getAttribute("value"));
            //ждем пока в списке брендов останется только первый элемент
            getCurrentPage().waitForLoadProduct(marketPage.brandsCheckboxesLoad);
            String beforeFilter = getCurrentPage().getElementByTitle("Приминение фильтра").getAttribute("data-bem");
            click("Первый элемент в списке производителей");
            getCurrentPage().waitForLoadList(beforeFilter, "Приминение фильтра");
            click("Чистка поля поиска");
        }
    }

    @Step("Устанавливаем {0} - {1}")
    void displayElements(String view, String count) {
        String beforeFilter = getCurrentPage().getElementByTitle("Ожидание обновления").getAttribute("data-bem");
        scroll(view);
        click(view);
        click(count);
        getCurrentPage().waitForLoadList(beforeFilter, "Ожидание обновления");
    }

    @Step("Сохранение первого элемента в списке")
    void saveElement(String element) {
        loadElements(element);
        MarketPage.savedValue = getCurrentPage().getElementByTitle(element).getAttribute("title");
    }

    @Step("Вводим сохраненное значение - {0}, в поисковую строку Маркета")
    void searchByValue(String savedValue) {
        scroll(savedValue);
        sendKeys(savedValue, MarketPage.savedValue);
    }

    @Step("Сравниваем два элемента")
    void compareTwoElements(String firstElement) {
        loadElements(firstElement);
        String valueOfFirstElement = getCurrentPage().getElementByTitle(firstElement).getAttribute("title");
        Assert.assertEquals("Наименования первого элемента списка не совпадает с сохраненным значением",
                MarketPage.savedValue,
                valueOfFirstElement);
    }

    @Step("Находим элемент - {0}")
    void scroll(String nameOfElement) {
        getCurrentPage().scrollToElement(getCurrentPage().getElementByTitle(nameOfElement));
    }

    @Step("Вводим значение {1} в элемент {0}")
    void sendKeys(String nameElement, String keysValue) {
        getCurrentPage().sendKeys(getCurrentPage().getElementByTitle(nameElement), keysValue);
    }

    @Step("Элемент {0} загружается")
    void loadElements(String load) {
        getCurrentPage().visible(getCurrentPage().getElementByTitle(load));
    }

    @Step("Нажимаем на кнопку - {0}")
    void click(String nameOfElement) {
        getCurrentPage().click(getCurrentPage().getElementByTitle(nameOfElement));
    }
}
