package ge.tbc.tbcitacademy.saucedemo.steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.annotations.AfterClass;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class InventorySteps {
    private static Connection connection;
    private static Statement statement;
    public static Statement getStatement(){
        return statement;
    }
    public static Connection getConnection(){
        return connection;
    }
    @Step("Checks if all image '{elements}' are displayed")
    public void checkAllImageIsDisplayed(ElementsCollection elements) {
        for (SelenideElement element : elements) {
            if (element.isDisplayed()) {
                return;
            }
        }
    }
    @Step("Checks if all image '{elements}' are same")
    public boolean checkIfImageIsDuplicated(ElementsCollection elements) {
        Set<String> set = new HashSet<>();
        for (SelenideElement element : elements) {
            if (!set.add(element.getAttribute("src")))
                return true;
        }
        return false;
    }
    @AfterClass
    public static void tearDown() {
        try {
            if (getStatement() != null) {
                getStatement().close();
            }
            if (getConnection() != null) {
                getConnection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
