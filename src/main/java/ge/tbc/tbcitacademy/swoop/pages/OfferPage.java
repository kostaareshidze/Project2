package ge.tbc.tbcitacademy.swoop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class OfferPage {
    public SelenideElement shareButton = $(byText("გაზიარება"));
}
