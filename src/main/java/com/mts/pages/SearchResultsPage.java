package com.mts.pages;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.SelenideElement;



import com.codeborne.selenide.ElementsCollection;

import java.util.Objects;

public class SearchResultsPage {
    private SelenideElement searchHeader = $("[name='ss']");
    private SelenideElement allFiltersButton = $("[data-filters-group='class']");
    private SelenideElement fiveStarFilter = $("[data-filters-item='class:class=5']");
    private ElementsCollection hotelRatings = $$("[data-testid='rating-stars']");

    public SearchResultsPage verifySearchHeaderContains(String expectedQuery) {
        String enteredValue = $("input[name='ss']").getValue(); // ID поля ввода

        // Сравниваем с ожидаемым значением
        if (Objects.equals(expectedQuery, enteredValue)){
            System.out.println("Значения совпадают");
            }
        else{
            System.out.println("Значения не совпадают");
        }
        return this;
    }

    public SearchResultsPage applyFiveStarFilter() {
        allFiltersButton.scrollTo();
        fiveStarFilter.click();
        return this;
    }

    public SearchResultsPage verifyAllHotelsAreFiveStar() {
        hotelRatings.forEach(rating -> {
            // Поднимаемся на уровень выше, чтобы получить родительский элемент
            SelenideElement parentElement = rating.closest("div"); // Поднимитесь на уровень выше
            parentElement.shouldHave(attribute("aria-label", "5 из 5"));  // Проверяем атрибут aria-label
        });
        return this;
    }

}
