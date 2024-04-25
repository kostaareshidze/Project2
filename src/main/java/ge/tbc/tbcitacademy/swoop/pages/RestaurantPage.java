package ge.tbc.tbcitacademy.swoop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import javax.swing.text.Element;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RestaurantPage {
    public SelenideElement firstOffer = $x("//div[@class='special-offer'][1]");
    public SelenideElement firstOfferFavoriteButton = firstOffer.$x(".//div[@class='deal-box-wishlist']");
    public SelenideElement progressBar = $x("//div[@class='special-offer'][1]//div[@class='voucher-limit']");
    public SelenideElement dropDown = $x(("//div[@class='category-filter-desk']//button"));
    public SelenideElement dighomi = $x("//div[@class='category-filter-desk']//input[@value='14']");
    public SelenideElement fullPay = $x("//div[@class='category-filter-desk']//input[@value='0']");
    public SelenideElement allPay = $x("//div[@class='category-filter-desk']//input[@value='2']");
    public SelenideElement delete = $x("//div[@class='category-filter-desk']//div[@class='delete-search-button']");
    public ElementsCollection offers = $$x("//div[@class='special-offer']");
}
