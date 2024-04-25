package ge.tbc.tbcitacademy.saucedemo.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class InventoryPage {
    public ElementsCollection inventories = $$x("//img[@class='inventory_item_img']");
    public SelenideElement threeLineButton = $("#react-burger-menu-btn");
    public SelenideElement logOutButton = $("#logout_sidebar_link");

    public static void main(String[] args) {
        System.out.println("daviwyet");
    }
}
