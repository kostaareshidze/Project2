package ge.tbc.tbcitacademy.allData;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

import java.io.InputStream;

public class CustomTestListener implements ITestListener {

    static {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(true)
                        .savePageSource(false));
    }
    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = WebDriverRunner.getWebDriver();
        InputStream screen = takeScreenshot(driver);
        Allure.addAttachment("Failed Screenshot", "image/png", screen, ".png");
    }
    private static InputStream takeScreenshot(WebDriver driver) {
        byte[] screenBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        return new ByteArrayInputStream(screenBytes);
    }

    public static void main(String[] args) {
        System.out.println("movrchit");
    }
}

