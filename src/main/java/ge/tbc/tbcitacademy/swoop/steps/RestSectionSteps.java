package ge.tbc.tbcitacademy.swoop.steps;
import com.codeborne.selenide.*;
import ge.tbc.tbcitacademy.swoop.pages.RestSectionPage;
import io.qameta.allure.Step;
import java.util.ArrayList;
import java.util.List;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.refresh;



public class RestSectionSteps {
    RestSectionPage restSectionPage = new RestSectionPage();
    BasicSteps basicSteps = new BasicSteps();
    @Step("giving '{minimumPrice}' to '{minPrice}' and '{maximumPrice}' to '{maxPrice}'")
    public RestSectionSteps givingValuesToPrice(SelenideElement minPrice, SelenideElement maxPrice,
                                                int minimumPrice, int maximumPrice) {
        minPrice.clear();
        minPrice.sendKeys(Integer.toString(minimumPrice));
        maxPrice.clear();
        maxPrice.sendKeys(Integer.toString(maximumPrice));
        return this;
    }
    @Step("click on rest section with button '{button}'")
    public RestSectionSteps submit(SelenideElement button) {
        basicSteps.click(button);
        return this;
    }
    @Step("Take prices from '{elements}'")
    public List<Integer> gettingAllPrice(ElementsCollection elements)  {
        List<Integer> prices = new ArrayList<>();
        refresh();
        for (SelenideElement element : elements){
            element.shouldBe(visible);
            String price = element.getText();
            String newPrice = price.substring(0, element.text().length() - 1);
            prices.add(Integer.parseInt(newPrice));
        }
        return prices;
    }
    @Step("Checking if price is in range '{min}' to '{max}'")
    public void isInRange(int min, int max){
        List<Integer> elements = gettingAllPrice(restSectionPage.prices);
        for (Integer element : elements){
            if (element >= min && element <= max){
                return;
            }
        }
    }
}
