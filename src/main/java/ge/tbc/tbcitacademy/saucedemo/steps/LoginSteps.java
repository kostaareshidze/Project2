package ge.tbc.tbcitacademy.saucedemo.steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ge.tbc.tbcitacademy.saucedemo.data.MSSQLConnection;
import ge.tbc.tbcitacademy.saucedemo.pages.LoginPage;
import io.qameta.allure.Step;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Condition.visible;

public class LoginSteps {
    LoginPage loginPage = new LoginPage();

    @Step("parse and take usernames from '{text}' and add to list")
    public List<String> users(String text) {
        List<String> users = new ArrayList<>();
        String pattern = "\\b\\w+_user\\b";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(text);
        while (matcher.find()) {
            users.add(matcher.group());
        }
        return users;
    }
    @Step("parse and take password from '{text}'")
    public String password(String text) {
        return text.substring(text.lastIndexOf("\n") + 1);
    }
    @Step("import usernames and password our database")
    public void importIntoDatabase() {
        try (Connection conn = MSSQLConnection.connect()) {
            String SQL = """
                    INSERT INTO Users (username, password) VALUES (?, ?)
                    """;
            PreparedStatement ps = conn.prepareStatement(SQL);
            for (int i = 0; i < users(loginPage.usersToParse).size(); i++) {
                String username = users(loginPage.usersToParse).get(i);
                ps.setString(1, username);
                ps.setString(2, password(loginPage.passwordToParse));
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Step("Take username and password with id and add to list")
    public List<String> getUsernameAndPassword(int id) {
        List<String> userNameAndPassword = new ArrayList<>();
        try (Connection conn = MSSQLConnection.connect()) {
            String SQL = """
                    SELECT username, password
                    FROM Users WHERE id = ?
                    """;
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                userNameAndPassword.add(rs.getString("username"));
                userNameAndPassword.add(rs.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userNameAndPassword;
    }
    @Step("put username from database to '{username}'")
    public LoginSteps putUsername(List<String> userNameAndPassword, SelenideElement username) {
        username.clear();
        username.sendKeys(userNameAndPassword.get(0));
        return this;
    }
    @Step("put password from database to '{password}'")
    public void putPassword(List<String> userNameAndPassword, SelenideElement password) {
        password.clear();
        password.sendKeys(userNameAndPassword.get(1));
    }
    @Step("check if this error '{element}' appeared")
    public boolean isErrorAppeared(SelenideElement errorMessage) {
        return errorMessage.isDisplayed();
    }
    @Step("check if this errors '{elements}' appeared")
    public boolean isErrorAppeared(ElementsCollection elements) {
        for (SelenideElement element : elements) {
            if (element.is(visible))
                return true;
        }
        return false;
    }
}
