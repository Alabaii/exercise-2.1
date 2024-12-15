package com.mts.pages;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.SelenideElement;

import com.codeborne.selenide.ElementsCollection;

public class SearchResultsPage {
    private SelenideElement searchHeader = $("[name='ss']");
    private SelenideElement allFiltersButton = $("[data-filters-group='class']");
    private SelenideElement fiveStarFilter = $("[data-filters-item='class:class=5']");
    private ElementsCollection hotelRatings = $$("[data-testid='rating-stars']");

    public SearchResultsPage verifySearchHeaderContains(String expectedText) {
        searchHeader.shouldHave(text(expectedText));
        return this;
    }

    public SearchResultsPage applyFiveStarFilter() {
        allFiltersButton.click();
        fiveStarFilter.click();
        return this;
    }

    public SearchResultsPage verifyAllHotelsAreFiveStar() {
        hotelRatings.forEach(rating -> rating.shouldHave(attribute("aria-label", "5 из 5")));
        return this;
    }
}
