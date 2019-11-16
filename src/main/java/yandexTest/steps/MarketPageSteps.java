package yandexTest.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import yandexTest.pages.MarketPage;

import java.util.List;


public class MarketPageSteps {

    private MarketPage marketPage = new MarketPage();

    @And("Выбран раздел \"(.*)\"")
    public void selectCategory(String categoryOfProducts) {
        marketPage.click(marketPage.sectionPC);
        marketPage.assertCheckTitle(categoryOfProducts);
    }

    @And("Выбран тип \"(.*)\"")
    public void selectType(String typeOfProducts) {
        marketPage.click(marketPage.typeLaptop);
        marketPage.assertCheckTitle(typeOfProducts);
    }

    @And("Установлена цена от \"(.*)\" до \"(.*)\" рублей")
    public void setPrice(String priceFrom, String priceTo) {
        marketPage.scrollToElement(marketPage.priceFrom);
        marketPage.sendKeys(marketPage.priceFrom, priceFrom);
        marketPage.assertEquals(priceFrom, marketPage.priceFrom.getAttribute("value"));
        marketPage.sendKeys(marketPage.priceTo, priceTo);
        marketPage.assertEquals(priceTo, marketPage.priceTo.getAttribute("value"));
    }

    @And("Выбраны производители")
    public void selectBrand(List<String> listOfBrands) {
        marketPage.scrollToElement(marketPage.showAllBrands);
        marketPage.click(marketPage.showAllBrands);
        for (String brand : listOfBrands) {
            marketPage.visible(marketPage.searchField);
            marketPage.sendKeys(marketPage.searchField, brand);
            Assert.assertEquals("Значение в поле задано неверно", brand, marketPage.searchField.getAttribute("value"));
            //ждем пока в списке брендов останется только первый элемент
            while (true) {
                if (marketPage.brandsCheckboxesLoad.size() == 1)
                    break;
            }
            marketPage.click(marketPage.firstCheckbox);
            marketPage.visible(marketPage.productsAfterUseFilter);
            marketPage.click(marketPage.clearSearchField);
        }
    }

    @Then("Выполнено ожидание результатов поиска")
    public void waitResults() {
        marketPage.visible(marketPage.waitUpdateList);
    }

    @When("Установлено количество отображаемых элементов на странице 12")
    public void setCountOfElementsOnPage() {
        marketPage.scrollToElement(marketPage.countProductsOnPage);
        marketPage.click(marketPage.countProductsOnPage);
        marketPage.click(marketPage.showTwelveProductsOnPage);
    }

    @Then("Выполнено ожидание обновления результатов")
    public void waitUpdateResults() {
        marketPage.visible(marketPage.waitUpdateList);
    }

    @When("Выполнена проверка отображения элементов на странице в количестве \"(.*)\" штук")
    public void checkDisplayOfElements(String count) {
        marketPage.visible(marketPage.checkTwelveProductsOnPage);
        Assert.assertEquals("Количество элементов на странице отображается неправильно",
                Integer.parseInt(count),
                marketPage.listOfProducts.size());
    }

    @Then("Сохранено наименование первого элемента в списке")
    public void saveFirstElementOfList() {
        marketPage.visible(marketPage.firstElementTitle);
        MarketPage.savedValue = marketPage.firstElementTitle.getAttribute("title");
    }

    @When("В поисковую строку введено сохраненное значение")
    public void writeSaveValueToSearchField() {
        marketPage.scrollToElement(marketPage.marketSearchField);
        marketPage.sendKeys(marketPage.marketSearchField, MarketPage.savedValue);
    }

    @And("Выполнено нажатие на кнопку 'Найти'")
    public void pressButtonFind() {
        marketPage.click(marketPage.marketSearchButton);
    }

    @Then("Произведена проверка наименования первого элемента списка с сохраненным значением")
    public void checkResultsOfCompare() {
        marketPage.visible(marketPage.firstElementTitle);
        String valueOfFirstElement = marketPage.firstElementTitle.getAttribute("title");
        Assert.assertEquals("Наименования первого элемента списка не совпадает с сохраненным значением",
                MarketPage.savedValue,
                valueOfFirstElement);
    }
}
