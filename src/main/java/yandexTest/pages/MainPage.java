package yandexTest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import yandexTest.annotation.ElementTitle;
import yandexTest.annotation.PageTitle;


@PageTitle("Яндекс")
public class MainPage extends BasePage {

    @ElementTitle("Маркет")
    @FindBy(xpath = "//div[contains(text(), 'Маркет')]")
    public WebElement categoryYandexMarket;


}
