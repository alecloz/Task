package yandexTest.steps;

import cucumber.api.java.en.When;
import yandexTest.pages.MainPage;


public class MainPageSteps {

    private MainPage mainPage = new MainPage();

    @When("Выполнен переход в \"(.*)\"")
    public void selectYandexCategory(String yandexCategory) {
        mainPage.click(mainPage.categoryYandexMarket);
        mainPage.assertCheckTitle(yandexCategory);

    }
}
