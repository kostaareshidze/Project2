package ge.tbc.tbcitacademy.tests.saucedemo;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import ge.tbc.tbcitacademy.allData.Constants;
import ge.tbc.tbcitacademy.allData.CustomTestListener;
import ge.tbc.tbcitacademy.saucedemo.pages.InventoryPage;
import ge.tbc.tbcitacademy.saucedemo.pages.LoginPage;
import ge.tbc.tbcitacademy.saucedemo.steps.InventorySteps;
import ge.tbc.tbcitacademy.saucedemo.steps.LoginSteps;
import ge.tbc.tbcitacademy.swoop.steps.BasicSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners({CustomTestListener.class})
@Test(groups = "SauceDemoLogin")
@Epic("Log In Page")
public class LoginTests {
    LoginSteps loginSteps;
    LoginPage loginPage;
    BasicSteps basicSteps;
    InventorySteps inventorySteps;
    InventoryPage inventoryPage;
    SoftAssert softAssert;
    @BeforeMethod
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("safebrowsing.enabled", false);
        options.setExperimentalOption("prefs", prefs);
        Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
        open(Constants.sauceDemoURL);
        WebDriverManager.chromedriver().setup();
        getWebDriver().manage().window().maximize();
        Configuration.timeout = 3000;
        loginPage = new LoginPage();
        loginSteps = new LoginSteps();
        basicSteps = new BasicSteps();
        inventorySteps = new InventorySteps();
        inventoryPage = new InventoryPage();
        softAssert = new SoftAssert();
    }
    @AfterMethod
    public static void tearDown(){
        InventorySteps.tearDown();
    }
    @Severity(SeverityLevel.NORMAL)
    @Feature("Database")
    @Story("Import Into Database")
    @Test(priority = 1, description = "imports usernames and password into database")
    public void importIntoDatabase() {
        loginSteps.importIntoDatabase();
    }
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Log In")
    @Story("Loading Images")
    @Test(priority = 10, description = "checking if all images are loaded")
    public void successfulLoginTest() {
        loginSteps.putUsername(loginSteps.getUsernameAndPassword(Constants.one), loginPage.username)
                .putPassword(loginSteps.getUsernameAndPassword(Constants.one), loginPage.password);
        basicSteps.click(loginPage.login);
        inventorySteps.checkAllImageIsDisplayed(inventoryPage.inventories);
    }
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Log In")
    @Story("Log In Error")
    @Test(description = "checks error messages visibility", priority = 3)
    public void bannedUserLoginTest() {
        loginSteps.putUsername(loginSteps.getUsernameAndPassword(Constants.two), loginPage.username)
                .putPassword(loginSteps.getUsernameAndPassword(Constants.two), loginPage.password);
        basicSteps.click(loginPage.login);
        softAssert.assertTrue(loginSteps.isErrorAppeared(loginPage.error));
        loginPage.errorX.shouldBe(visible);
        softAssert.assertTrue(loginSteps.isErrorAppeared(loginPage.doubleErrorX));
        softAssert.assertAll();

    }
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Log In")
    @Story("Log In Problem")
    @Test(description = "checks that all images are same", priority = 4)
    public void problematicLoginTest() {
        loginSteps.putUsername(loginSteps.getUsernameAndPassword(Constants.three), loginPage.username)
                .putPassword(loginSteps.getUsernameAndPassword(Constants.three), loginPage.password);
        basicSteps.click(loginPage.login);
        softAssert.assertFalse(inventorySteps.checkIfImageIsDuplicated(inventoryPage.inventories));
        softAssert.assertAll();
        //if it is duplicated then it is not loaded fine
    }
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Log Out")
    @Story("Log Out Validation")
    @Test(description = "checks log out functionality", priority = 5)
    public void logOutTest() {
        loginSteps.putUsername(loginSteps.getUsernameAndPassword(Constants.one), loginPage.username)
                .putPassword(loginSteps.getUsernameAndPassword(Constants.one), loginPage.password);
        basicSteps.click(loginPage.login)
                .click(inventoryPage.threeLineButton)
                .click(inventoryPage.logOutButton);
        loginPage.username.shouldNotBe(empty); // Failing on purpose to screenshot
        loginPage.password.shouldNotBe(empty);
    }
}
