package com.mts.tests;
import io.qameta.allure.Allure;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import com.mts.pages.HomePage;
import com.mts.pages.SearchResultsPage;


import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;




import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import static com.codeborne.selenide.Selenide.*;
import io.qameta.allure.*;

@Epic("Функциональность бронирования")
@Feature("Поиск отелей")
public class BookingTest {

    @BeforeAll
    public static void setupAll() {
        // Этот метод будет вызван перед всеми тестами
        System.out.println("Настройка окружения...");
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(true).savePageSource(true));

        // Настройка Chrome в режиме инкогнито
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito"); // Режим инкогнито
        options.addArguments("--start-maximized"); // Открыть на полный экран
        options.addArguments("--disable-notifications"); // Отключить уведомления
        options.addArguments("--disable-geolocation");
        options.addArguments("--no-restore-session-state");
        options.addArguments("--disable-location-services");
        options.addArguments("--disable-cache");

        // Передача настроек браузеру
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;

        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
    }

    @BeforeEach
    public void setup() {
        // Этот метод будет вызываться перед каждым тестом
        System.out.println("Запуск теста поиска отелей...");
    }

    @Test
    @DisplayName("Тест поиска отелей в Анталье")
    @Tag("critical")
    @Description("Тест поиска отелей с фильтром пятизвездочных отелей в Анталье")
    public void testBookingSearch() {

    Allure.step("Открытие главной страницы", () -> {
        new HomePage()
            .openHomePage();
        });
    Allure.step("Отклонить куки", () -> {
        new HomePage()
                .rejectCookies();
        });
    Allure.step("Ввод города и клик по кнопке", () -> {
            new HomePage()
                    .enterSearchQuery("Анталья")
                    .clickSearchButton();
        });
    Allure.step("Сравнение города с тем что в вводили", () -> {
            new SearchResultsPage()
                    .verifySearchHeaderContains("Прага");
        });
    Allure.step("Применение фильтра 5 звезд", () -> {
            new SearchResultsPage()
                    .applyFiveStarFilter();
        });

    Allure.step("Проверка наличия отелей", () -> {
            new SearchResultsPage()
                    .verifyAllHotelsAreFiveStar();
        });



    }
}
