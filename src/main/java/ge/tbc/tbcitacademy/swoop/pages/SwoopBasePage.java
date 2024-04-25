package ge.tbc.tbcitacademy.swoop.pages;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SwoopBasePage {
    public SelenideElement rest = $x("//a[div[contains(@class, 'MenuIcon')] and normalize-space() = 'დასვენება']");
    public SelenideElement category = $(".NewCategories.newcat");
    public SelenideElement eat = $x("//li[@cat_id='CatId-3']");
    public SelenideElement restaurant = $(byText("რესტორანი"));

}
