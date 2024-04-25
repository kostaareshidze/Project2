package ge.tbc.tbcitacademy.swoop.steps;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class OfferSteps {
    BasicSteps basicSteps = new BasicSteps();
    @Step("Clicks on share button '{element}' ")
    public OfferSteps share(SelenideElement element){
        basicSteps.click(element);
        return this;
    }
    @Step("Checks if url of link is facebook")
    public boolean validateNewWindowIsFacebook(){
        return getWebDriver().getCurrentUrl().contains("https://www.facebook.com/");
    }
}
