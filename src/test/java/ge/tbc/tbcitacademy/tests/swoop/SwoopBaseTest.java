package ge.tbc.tbcitacademy.tests.swoop;
import com.codeborne.selenide.Configuration;
import ge.tbc.tbcitacademy.allData.Constants;
import ge.tbc.tbcitacademy.allData.CustomTestListener;
import ge.tbc.tbcitacademy.swoop.data.DataProviders;
import ge.tbc.tbcitacademy.swoop.pages.RestSectionPage;
import ge.tbc.tbcitacademy.swoop.pages.SwoopBasePage;
import ge.tbc.tbcitacademy.swoop.steps.BasicSteps;
import ge.tbc.tbcitacademy.swoop.steps.RestSectionSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners({CustomTestListener.class})
@Test(groups = "SwoopRegression")
@Epic("Price Filters")
public class SwoopBaseTest {
    SwoopBasePage swoopBasePage;
    RestSectionPage restSectionPage;
    RestSectionSteps restSectionSteps;
    BasicSteps basicSteps;
    private boolean setUp = false;
    @BeforeClass
    public void setUp() {
        Configuration.timeout = 10000;
        WebDriverManager.chromedriver().setup();
        open(Constants.swoopURL);
        getWebDriver().manage().window().maximize();
        swoopBasePage = new SwoopBasePage();
        restSectionPage = new RestSectionPage();
        restSectionSteps = new RestSectionSteps();
        basicSteps = new BasicSteps();
    }
    @AfterClass
    public void tearDown() {
        getWebDriver().quit();
    }
    @Severity(SeverityLevel.NORMAL)
    @Feature("Price Range")
    @Story("Checking Price Range")
    @Test(dataProvider = "givePriceValues", dataProviderClass = DataProviders.class, priority = 1,
            description = "checks if offers are in given range")
    public void OfferTests (int minPrice, int maxPrice) {
        if (!setUp){
        basicSteps.goToSection(swoopBasePage.rest);
            setUp = true;
        }// because I want it to executed once
        restSectionSteps.givingValuesToPrice(restSectionPage.minPrice,
                        restSectionPage.maxPrice, minPrice, maxPrice)
                .submit(restSectionPage.submit)
                .isInRange(minPrice, maxPrice);
    }
}
