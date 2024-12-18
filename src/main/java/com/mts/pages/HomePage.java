package com.mts.pages;



import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

import com.codeborne.selenide.SelenideElement;

public class HomePage {
    // Локатор обновлен для поля поиска
    private SelenideElement searchField = $("[name='ss']");
    private SelenideElement searchButton = $x("//button[.//span[text()='Найти']]");

    public HomePage openHomePage() {
        open("https://booking.com/");
        return this;
    }
    public HomePage rejectCookies() {
        $("#onetrust-reject-all-handler").click(); // Нажимаем на кнопку с указанным id
        return this;
    }

    public HomePage enterSearchQuery(String query) {
        searchField.setValue(query);
        return this;
    }

    public SearchResultsPage clickSearchButton() {
        searchButton.click();
        return new SearchResultsPage();
    }
}
