package ge.tbc.tbcitacademy.swoop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class RestSectionPage {
    public SelenideElement minPrice = $("#sidebar-container #minprice");
    public SelenideElement maxPrice = $("#sidebar-container #maxprice");
    public SelenideElement submit = $("#sidebar-container .submit-button");
    public ElementsCollection prices = $$x("//div[@class='special-offer']//div[@class='discounted-prices']//p[@class='deal-voucher-price' and not(@style)]");
}
