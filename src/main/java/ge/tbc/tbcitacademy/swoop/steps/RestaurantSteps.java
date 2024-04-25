package ge.tbc.tbcitacademy.swoop.steps;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import java.util.Objects;


public class RestaurantSteps {
    BasicSteps basicSteps = new BasicSteps();
    @Step("Clicks on add to favorite '{element}' ")
    public void addToFavorite(SelenideElement element) {
        basicSteps.click(element);
    }
    @Step("Checks if url is auth.net.ge")
    public boolean validateLogInPage(){
        return WebDriverRunner.url().contains("https://auth.tnet.ge/");
    }
    @Step("Removes width: from '{text}'")
    public String removeWidth(String text){
        return text.replaceAll("[^0-9%]", "");
    }
    @Step("checks if style of '{element}' is 100%")
    public boolean checkIfVouchersAreSold(SelenideElement element) {
        WebDriverRunner.getWebDriver().navigate().back();
        return removeWidth(Objects.requireNonNull(element.getAttribute("style"))).equals("100%");
    }
    @Step("checks if each element from '{elements}' dont have text გაყიდულია 0")
    public SelenideElement checkIfVouchersAreNotSold(ElementsCollection elements) {
        for (SelenideElement element : elements) {
            if (element.$x(".//div[@class='voucher-counts']//p[not(@*)]").text().equals("გაყიდულია 0"))
                return element;
        }
        return null;
    }
    @Step("Check if progress bar '{element}' style is 0%")
    public boolean checkProgressBarIsEmpty(SelenideElement element) {
        return removeWidth(Objects.requireNonNull(element
                .$x(".//div[@class='voucher-limit']").getAttribute("style"))).equals("0%");
    }
}
