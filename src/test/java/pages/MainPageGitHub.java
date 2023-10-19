package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class MainPageGitHub {

    SelenideElement searhButton = $(".header-search-button");
    SelenideElement searchInput = $("#query-builder-test");

    @Step("Поиск репозитория по названию : {repository}")
    public MainPageGitHub searchRepository(String repository) {
        searhButton.click();
        searchInput.setValue(repository).pressEnter();
        return this;
    }


}