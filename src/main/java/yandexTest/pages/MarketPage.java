package yandexTest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class MarketPage extends BasePageObject {

    public static String savedValue;


    @FindBy(xpath = "//span[contains(text(), 'Компьютерная техника')]")
    public WebElement sectionPC;

    @FindBy(xpath = "//a[contains(text(), 'Ноутбуки')]")
    public WebElement typeLaptop;

    @FindBy(xpath = "//input[@id='glpricefrom']")
    public WebElement priceFrom;

    @FindBy(xpath = "//input[@id='glpriceto']")
    public WebElement priceTo;

    @FindBy(xpath = "//legend[contains(text(),'Производитель')]/ancestor::div[1]//button[contains(text(), 'Показать всё')]")
    public WebElement showAllBrands;

    @FindBy(xpath = "//legend[contains(text(),'Производитель')]/ancestor::div[1]//input[@name = 'Поле поиска']")
    public WebElement searchField;

    @FindBy(xpath = "//legend[contains(text(),'Производитель')]/ancestor::div[1]//input[@type = 'checkbox']/ancestor::label[1]")
    public WebElement firstCheckbox;

    @FindBy(xpath = "//legend[contains(text(),'Производитель')]/ancestor::fieldset//a")
    public List<WebElement> brandsCheckboxesLoad;

    @FindBy(xpath = "//legend[contains(text(),'Производитель')]/ancestor::div[1]//nav/label")
    public WebElement clearSearchField;

    @FindBy(xpath = "//div[@data-d6578eea = 'true']")
    public WebElement productsAfterUseFilter;

    @FindBy(xpath = "//button[@role = 'listbox']")
    public WebElement countProductsOnPage;

    @FindBy(xpath = "//span[contains(text(), 'Показывать по 12')]")
    public WebElement showTwelveProductsOnPage;

    @FindBy(xpath = "//div[@style = 'height: auto;' and contains(@class, 'n-filter')]")
    public WebElement waitUpdateList;

    @FindBy(xpath = "//div[contains(@data-bem, '\"products\":{\"count\":12}')]")
    public WebElement checkTwelveProductsOnPage;

    @FindBy(xpath = "//div[contains(@data-bem, 'products')]/child::*")
    public List<WebElement> listOfProducts;

    @FindBy(xpath = "//div[contains(@data-bem, '\"products\":{\"count\"')]/div[1]//div[@class = 'n-snippet-card2__title']/a")
    public WebElement firstElementTitle;

    @FindBy(xpath = "//input[@id='header-search']")
    public WebElement marketSearchField;

    @FindBy(xpath = "//span[@class='search2__button']/child::button")
    public WebElement marketSearchButton;


}


