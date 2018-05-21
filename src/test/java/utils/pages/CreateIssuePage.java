package utils.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.elements.Issue;

public class CreateIssuePage extends BasicPage {

    private static final String SUMMARY_ID = "id_l.I.ni.ei.eit.summary";
    private static final String DESCRIPTION_ID = "id_l.I.ni.ei.eit.description";
    private static final String SUBMIT_BUTTON_CN = "l.I.ni.ei.submitButton";
    private static final String ERROR_CLASS = "errorSeverity";

    private WebElement summaryInput;
    private WebElement descriptionInput;
    private WebElement submitButton;
    private String lastAddress;

    public CreateIssuePage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public void redirect(String address) {
        lastAddress = address;
        driver.get(address + "issues#newissue=yes");
    }

    public void create(Issue issue) {
        createBase(issue);
        driverWait.until(ExpectedConditions.urlMatches(lastAddress + "issues#%20"));
    }

    public void createWithError(Issue issue) {
        createBase(issue);
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ERROR_CLASS)));
    }

    private void createBase(Issue issue) {
        linkElements();
        summaryInput.clear();
        summaryInput.sendKeys(issue.getSummary());
        descriptionInput.clear();
        descriptionInput.sendKeys(issue.getDescription());
        submitButton.click();
    }

    private void linkElements() {
        summaryInput = waitById(SUMMARY_ID);
        descriptionInput = waitById(DESCRIPTION_ID);
        submitButton = waitByCss("*[cn='" + SUBMIT_BUTTON_CN + "']");
    }
}
