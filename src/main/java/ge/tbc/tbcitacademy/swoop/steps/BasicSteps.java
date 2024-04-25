package ge.tbc.tbcitacademy.swoop.steps;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.switchTo;

public class BasicSteps {
    @Step("Clicks on element '{element}' ")
    public BasicSteps click(SelenideElement element) {
        element.shouldBe(clickable).click();
        return this;
    }
    @Step("Clicks on element '{element}' ")
    public void goToSection(SelenideElement element) {
        click(element);
    }
    @Step("Hovers on element '{element}' ")
    public BasicSteps hover(SelenideElement element) {
        element.shouldBe(visible).hover();
        return this;
    }
    @Step("Switch to frame with index '{index}' ")
    public void switchToNewFrame(int index){
        switchTo().window(index);
    }
    @Step("Switch to default content")
    public void defaultContent(){
        switchTo().defaultContent();
    }
    @Step("Checks button '{element}' ")
    public void checkRadioButton(SelenideElement element){
        element.setSelected(true);
    }
    @Step("Clears String inside input '{element}'")
    public BasicSteps clearInput(SelenideElement element){
        element.clear();
        return this;
    }
}
