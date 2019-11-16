package yandexTest.pages;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import yandexTest.core.DriverManager;

import static yandexTest.core.DriverManager.getDriver;


public abstract class BasePageObject {

    private WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 60);

    public BasePageObject() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void sendKeys(WebElement element, String keys) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(keys);
    }

    public void visible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("return arguments[0].scrollIntoView(true);", element);
    }

    public void assertCheckTitle(String title) {
        Assert.assertTrue("Переход на страницу " + title + " не выполнен", (getDriver()
                .getTitle()
                .matches(".*\\b" + title + "\\b.*")));
    }

    public void assertEquals(String expected, String actual) {
        Assert.assertEquals("Значения не равны", expected, actual);
    }

}
