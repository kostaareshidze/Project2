package ge.tbc.tbcitacademy.tests.swoop;

import com.codeborne.selenide.Configuration;
import ge.tbc.tbcitacademy.allData.Constants;
import ge.tbc.tbcitacademy.allData.CustomTestListener;
import ge.tbc.tbcitacademy.swoop.pages.OfferPage;
import ge.tbc.tbcitacademy.swoop.pages.RestSectionPage;
import ge.tbc.tbcitacademy.swoop.pages.RestaurantPage;
import ge.tbc.tbcitacademy.swoop.pages.SwoopBasePage;
import ge.tbc.tbcitacademy.swoop.steps.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners({CustomTestListener.class})
@Test(groups = "SwoopRegression")
@Epic("Swoop Functions")
public class CategoryTest {
    SwoopBasePage swoopBasePage;
    RestSectionPage restSectionPage;
    SoftAssert softAssert;
    BasicSteps basicSteps;
    RestaurantPage restaurantPage;
    RestaurantSteps restaurantSteps;
    OfferSteps offerSteps;
    OfferPage offerPage;
    @BeforeClass
    public void setUp() {
        Configuration.timeout = 3000;
        WebDriverManager.chromedriver().setup();
        open(Constants.swoopURL);
        getWebDriver().manage().window().maximize();
        swoopBasePage = new SwoopBasePage();
        restSectionPage = new RestSectionPage();
        restaurantPage = new RestaurantPage();
        restaurantSteps = new RestaurantSteps();
        offerPage = new OfferPage();
        offerSteps = new OfferSteps();
        softAssert = new SoftAssert();
        basicSteps = new BasicSteps();
    }
    @AfterClass
    public void tearDown() {
        getWebDriver().quit();
    }
    @Severity(SeverityLevel.NORMAL)
    @Feature("Favorite")
    @Story("Favorite")
    @Test(priority = 1, description = "checking if offer added to favorites")
    public void favouriteOfferTest() {
        basicSteps.click(swoopBasePage.category)
                .hover(swoopBasePage.eat)
                .click(swoopBasePage.restaurant);
        restaurantSteps.addToFavorite(restaurantPage.firstOfferFavoriteButton);
        softAssert.assertTrue(restaurantSteps.validateLogInPage());
        softAssert.assertFalse(restaurantSteps.checkIfVouchersAreSold(restaurantPage.progressBar));
        softAssert.assertAll();
    }
    @Severity(SeverityLevel.NORMAL)
    @Feature("Share")
    @Story("Share Offer")
    @Test(priority = 2, description = "checking if we can share offer")
    public void shareOfferTest() {
        basicSteps.click(swoopBasePage.category)
                .hover(swoopBasePage.eat)
                .click(swoopBasePage.restaurant)
                .click(restaurantPage.firstOffer);
        offerSteps.share(offerPage.shareButton);
        basicSteps.switchToNewFrame(1);
        softAssert.assertTrue(offerSteps.validateNewWindowIsFacebook());
        basicSteps.defaultContent();
        softAssert.assertAll();
    }
    @Severity(SeverityLevel.NORMAL)
    @Feature("Filters")
    @Story("Filters Functionality")
    @Test(description = "checking if filters are clear after refreshing")
    public void clearFilterTest() {
        basicSteps.click(swoopBasePage.category)
                .hover(swoopBasePage.eat)
                .click(swoopBasePage.restaurant)
                .click(restaurantPage.dropDown)
                .click(restaurantPage.dighomi)
                .click(restaurantPage.dropDown)
                .checkRadioButton(restaurantPage.fullPay);
        restSectionPage.minPrice.sendKeys(Constants.hundred);
        basicSteps.click(restaurantPage.delete);
        restSectionPage.minPrice.shouldBe(empty);
        restaurantPage.allPay.shouldBe(selected);
        restaurantPage.dropDown.shouldHave(text(Constants.allLocations));
    }
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Voucher")
    @Story("Voucher Count")
    @Test(description = "checks if vouchers are sold out")
    public void noOffersSoldTest() {
        basicSteps.click(swoopBasePage.category)
                .hover(swoopBasePage.eat)
                .click(swoopBasePage.restaurant);
        softAssert.assertTrue(restaurantSteps.checkProgressBarIsEmpty(restaurantSteps
                .checkIfVouchersAreNotSold(restaurantPage.offers)));
        softAssert.assertAll();

    }
}
