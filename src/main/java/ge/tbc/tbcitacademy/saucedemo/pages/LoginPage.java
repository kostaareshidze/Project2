package ge.tbc.tbcitacademy.saucedemo.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    public String usersToParse = $("#login_credentials").text();
    public String passwordToParse = $(".login_password").text();
    public SelenideElement username = $("#user-name");
    public SelenideElement password = $("#password");
    public SelenideElement login = $("#login-button");
    public SelenideElement error = $x("//h3");
    public SelenideElement errorX = $(".error-button");
    public ElementsCollection doubleErrorX = $$(".svg-inline--fa.fa-times-circle");

    public static void main(String[] args) {
        System.out.println("daviwyet");
    }
}
