import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPageGitHub;
import pages.ListResultSearchPage;
import pages.RepositoryPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;


@Story("Отображение Issue")
@Severity(SeverityLevel.MINOR)
@Link(url = "https://github.com")
public class IssueTests extends TestBase {

    MainPageGitHub mainPageGitHub = new MainPageGitHub();
    ListResultSearchPage listResultSearchPage = new ListResultSearchPage();
    RepositoryPage repositoryPage = new RepositoryPage();

    private static final String REPOSITORY = "kaliaaaaan/junit_test";
    private static final String ISSUE_NUMBER = "2";
    private static final String ISSUE_NAME = "Test issue 2";

    @Test
    @Feature("Annotated Steps")
    @DisplayName("Проверка имени " + ISSUE_NAME + " и номера: " + ISSUE_NUMBER + " в Issues")
    void checkIssueAnnotatedSteps() {
        open("");
        mainPageGitHub.searchRepository(REPOSITORY);
        listResultSearchPage.selectRepository(REPOSITORY);
        repositoryPage
                .selectIssuesTab()
                .checkIssueName(ISSUE_NUMBER, ISSUE_NAME);
    }

    @Test
    @Feature("With Clean")
    @DisplayName("Проверка имени " + ISSUE_NAME + " и номера: " + ISSUE_NUMBER + " в Issues")
    void checkIssueWithClean() {
        open("");
        $(".header-search-button").click();
        $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        $(linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $(String.format("#issue_%s_link", ISSUE_NUMBER)).shouldHave(text(ISSUE_NAME));
    }

    @Test
    @Feature("Lambda Steps")
    @DisplayName("Проверка имени " + ISSUE_NAME + " и номера: " + ISSUE_NUMBER + " в Issues")
    void checkIssueLambdaSteps() {
        open("");

        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".search-input").click();
            $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        });

        step("В списке результатов находим " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });

        step("Нажимаем на вкладку Issues", () ->
                $("#issues-tab").click()
        );
        step("Проверяем имя " + ISSUE_NAME + " и номера: " + ISSUE_NUMBER, () ->
                $(String.format("#issue_%s_link", ISSUE_NUMBER)).shouldHave(text(ISSUE_NAME))
        );
    }

}