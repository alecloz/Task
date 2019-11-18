package yandexTest.steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import yandexTest.core.DriverManager;
import yandexTest.core.TestProperties;

import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class BaseSteps {

    private static Properties properties = TestProperties.getInstance().getProperties();
    public static WebDriverWait webDriverWait;

    @Before
    public void startScenario() {
        WebDriver driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.get(properties.getProperty("app.url"));
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(driver, 10, 1000);
    }

    @After
    public void afterMethod(Scenario scenario) {
        if (scenario.isFailed()) {
            takeScreenshot();
        }
        DriverManager.quitDriver();
    }

    @Attachment(type = "image/png", value = "Скриншот в момент ошибки")
    private static byte[] takeScreenshot() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
