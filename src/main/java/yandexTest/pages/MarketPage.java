package yandexTest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import yandexTest.annotation.ElementTitle;
import yandexTest.annotation.PageTitle;

import java.util.List;

@PageTitle("Яндекс.Маркет")
public class MarketPage extends BasePage {

    public static String savedValue;

    @ElementTitle("Компьютерная техника")
    @FindBy(xpath = "//span[contains(text(), 'Компьютерная техника')]")
    public WebElement sectionPC;

    @ElementTitle("Ноутбуки")
    @FindBy(xpath = "//a[contains(text(), 'Ноутбуки')]")
    public WebElement typeLaptop;

    @ElementTitle("Цена от")
    @FindBy(xpath = "//input[@id='glpricefrom']")
    public WebElement priceFrom;

    @ElementTitle("Цена до")
    @FindBy(xpath = "//input[@id='glpriceto']")
    public WebElement priceTo;

    @ElementTitle("Показать всё")
    @FindBy(xpath = "//legend[contains(text(),'Производитель')]/ancestor::div[1]//button[contains(text(), 'Показать всё')]")
    public WebElement showAllBrands;

    @ElementTitle("Поле поиска")
    @FindBy(xpath = "//legend[contains(text(),'Производитель')]/ancestor::div[1]//input[@name = 'Поле поиска']")
    public WebElement searchField;

    @ElementTitle("Первый элемент в списке производителей")
    @FindBy(xpath = "//legend[contains(text(),'Производитель')]/ancestor::div[1]//input[@type = 'checkbox']/ancestor::label[1]")
    public WebElement firstCheckbox;

    @ElementTitle("Загрузка результатов поиска")
    @FindBy(xpath = "//legend[contains(text(),'Производитель')]/ancestor::fieldset//a")
    public List<WebElement> brandsCheckboxesLoad;

    @ElementTitle("Чистка поля поиска")
    @FindBy(xpath = "//legend[contains(text(),'Производитель')]/ancestor::div[1]//nav/label")
    public WebElement clearSearchField;

    @ElementTitle("Приминение фильтра")
    @FindBy(xpath = "//*[@class='i-bem n-pager-more n-pager-more_js_inited']")
    public WebElement filteredProducts;

    @ElementTitle("Отображение элементов")
    @FindBy(xpath = "//button[@role = 'listbox']")
    public WebElement countProductsOnPage;

    @ElementTitle("Показывать по 12")
    @FindBy(xpath = "//span[contains(text(), 'Показывать по 12')]")
    public WebElement showTwelveProductsOnPage;

    @ElementTitle("Ожидание обновления")
    @FindBy(xpath = "//div[contains(@class,'i-bem metrika_js_inited')]")
    public WebElement waitUpdateList;

    @ElementTitle("12")
    @FindBy(xpath = "//div[contains(@data-bem, '\"products\":{\"count\":12}')]")
    public WebElement checkTwelveProductsOnPage;

    @ElementTitle("Список продуктов")
    @FindBy(xpath = "//div[contains(@data-bem, 'products')]/child::*")
    public List<WebElement> listOfProducts;

    @ElementTitle("Первый элемент списка")
    @FindBy(xpath = "//div[contains(@data-bem, '\"products\":{\"count\"')]/div[1]//div[@class = 'n-snippet-card2__title']/a")
    public WebElement firstElementTitle;

    @ElementTitle("Поле поиска Маркета")
    @FindBy(xpath = "//input[@id='header-search']")
    public WebElement marketSearchField;

    @ElementTitle("Найти")
    @FindBy(xpath = "//span[@class='search2__button']/child::button")
    public WebElement marketSearchButton;


}


