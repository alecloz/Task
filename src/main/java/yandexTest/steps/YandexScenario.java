package yandexTest.steps;

import cucumber.api.java.en.When;
import yandexTest.core.DriverManager;
import yandexTest.pages.BasePage;
import yandexTest.pages.MarketPage;

import java.util.List;

import static yandexTest.core.PageManager.getCurrentPage;

public class YandexScenario {

    private AllSteps steps;
    private final BasePage basePage = new BasePage();
    private final MarketPage marketPage = new MarketPage();

    @When("Открыт сайт yandex.ru")
    public void start() {
        DriverManager.getDriver();
        steps = new AllSteps();
    }

    @When("На главной странице - \"(.+)\" выбрать подраздел \"(.+)\"")
    public void selectCategoryYandex(String page, String categoryName) {
        steps.openPage(page);
        basePage.assertCheckTitle(page);
        steps.click(categoryName);
        basePage.refreshWindowHandle();
    }

    @When("Выполнить переход на страницу - \"(.+)\"")
    public void changePage(String page) {
        steps.openPage(page);
        basePage.assertCheckTitle(page);
    }

    @When("Выбрать раздел \"(.+)\"")
    public void selectCategory(String categoryOfProduct) {
        steps.click(categoryOfProduct);
        basePage.refreshWindowHandle();
        basePage.assertCheckTitle(categoryOfProduct);
    }

    @When("В поле \"(.+)\" ввести \"(.+)\"")
    public void setPrice(String fieldName, String price) {
        steps.setPrice(fieldName, price);
        basePage.assertEquals(price, getCurrentPage().getElementByTitle(fieldName).getAttribute("value"));
    }

    @When("Нажать \"(.+)\" и выбрать бренды")
    public void selectBrands(String buttonName, List<String> list) {
        steps.setBrands(buttonName, list);
    }

    @When("Найдены \"(.+)\"")
    public void selectBrands(String filterElements) {
        steps.loadElements(filterElements);
    }

    @When("Выполнить \"(.+)\" результатов поиска")
    public void waitLoadElements(String load) {
        steps.clickableElement(load);
    }

    @When("Установить \"(.+)\" - \"(.+)\"")
    public void setDisplayElementsOnPage(String display, String numberOfElements) {
        steps.displayElements(display, numberOfElements);
    }

    @When("Выполнить проверку на странице отображается \"(.+)\" элементов")
    public void checkDisplayElementsOnPage(String countOfElements) {
        steps.loadElements(countOfElements);
        basePage.assertEquals(countOfElements, String.valueOf(marketPage.listOfProducts.size()));
    }

    @When("Сохранить \"(.+)\"")
    public void saveFirstElement(String firstElement) {
        steps.saveElement(firstElement);
    }

    @When("В \"(.+)\" ввести сохраненное значение")
    public void writeSaveValueToSearchField(String savedValue) {
        steps.searchByValue(savedValue);
    }

    @When("Выполнить нажатие на кнопку \"(.+)\"")
    public void clickButton(String nameButton) {
        steps.click(nameButton);
    }

    @When("Произвести проверку \"(.+)\" равен сохраненному значению")
    public void checkResult(String firstElement) {
        steps.compareTwoElements(firstElement);
    }
}
