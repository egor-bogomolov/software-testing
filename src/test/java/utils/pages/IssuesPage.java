package utils.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.elements.Issue;

import java.util.List;
import java.util.stream.Collectors;

public class IssuesPage extends BasicPage {

    private static final String ISSUE_LIST_ID = "id_l.I.c.il.issueList";
    private static final String ISSUE_CONTAINER_CSS = "div[cn='l.I.c.il.i.issueContainer']";
    private static final String ISSUE_SUMMARY_CLASS = "issue-summary";
    private static final String ISSUE_DESCRIPTION_CLASS = "description";

    private WebElement issueList;

    public IssuesPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public void redirect(String address) {
        driver.get(address + "issues/");
    }

    public List<Issue> getIssues() {
        linkElements();
        return issueList.findElements(By.cssSelector(ISSUE_CONTAINER_CSS))
                .stream()
                .map(webElement -> {
                    String summary = webElement.findElement(By.className(ISSUE_SUMMARY_CLASS)).getText();
                    List<WebElement> descCandidates = webElement.findElements(By.className(ISSUE_DESCRIPTION_CLASS));
                    String description = descCandidates.isEmpty() ? "" : descCandidates.get(0).getText();
                    return new Issue(summary, description);
                })
                .collect(Collectors.toList());
    }

    private void linkElements() {
        issueList = waitById(ISSUE_LIST_ID);
    }

}
