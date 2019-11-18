package yandexTest.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import yandexTest.annotation.ElementTitle;
import yandexTest.core.DriverManager;
import yandexTest.steps.BaseSteps;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static yandexTest.core.PageManager.getCurrentPage;


public class BasePage {

    private WebDriverWait wait = BaseSteps.webDriverWait;

    public BasePage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    @Step("Кликаем по элементу")
    public void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    @Step("Вводим значение в поле")
    public void sendKeys(WebElement element, String keys) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(keys);
    }

    @Step("Ожидаем загрузку элемента")
    public void visible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    @Step("Прокручиваем экран до элемента")
    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("return arguments[0].scrollIntoView(true);", element);
    }

    public void assertCheckTitle(String title) {
        Assert.assertTrue("Переход на страницу " + title + " не выполнен", (DriverManager.getDriver()
                .getTitle()
                .matches(".*\\b" + title + "\\b.*")));
    }

    public void assertEquals(String expected, String actual) {
        Assert.assertEquals("Значения не равны", expected, actual);
    }

    public WebElement waitForReadyElement(WebElement element) {
        visible(element);
        return element;
    }

    @Step("Поиск элемента по аннотации")
    public WebElement getElementByTitle(String title) {
        Field field = Arrays.stream(this.getClass().getDeclaredFields())
                .filter(f -> f.getType().equals(WebElement.class))
                .filter(f -> f.getAnnotation(ElementTitle.class) != null)
                .filter(f -> f.getAnnotation(ElementTitle.class).value().equalsIgnoreCase(title))
                .findFirst().orElseThrow(() -> new RuntimeException("Не найден элемент с названием " + title));
        Assert.assertEquals(field.getType(), WebElement.class);
        field.setAccessible(true);
        WebElement element = null;
        try {
            element = (WebElement) field.get(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return waitForReadyElement(element);
    }

    public void waitForLoadProduct(List<WebElement> list) {
        new WebDriverWait(DriverManager.getDriver(), 30)
                .withMessage(String.format("Значение равно [%d]. Ожидалось - [%d]", list.size(), 1))
                .until((ExpectedCondition<Boolean>) driver -> list.size() == 1);
    }

    public void waitForLoadList(String beforeValue, String titleOfElement) {
        new WebDriverWait(DriverManager.getDriver(), 30)
                .withMessage(String.format("Значение равно [%s]. Ожидалось - [%s]", getCurrentPage()
                        .getElementByTitle(titleOfElement)
                        .getAttribute("data-bem"), beforeValue))
                .until((ExpectedCondition<Boolean>) driver -> !(getCurrentPage().getElementByTitle(titleOfElement)
                        .getAttribute("data-bem"))
                        .equals(beforeValue));
    }
}
