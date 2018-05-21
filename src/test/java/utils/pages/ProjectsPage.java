package utils.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ProjectsPage extends BasicPage {

    private static final String NAME_ID = "id_l.C.EditProjectMain.projectName";
    private static final String ID_ID = "id_l.C.EditProjectMain.shortName";
    private static final String STARTING_NUMBER_ID = "id_l.C.EditProjectMain.nextIssueNumber";
    private static final String PROJECT_LEAD_ID = "id_l.C.EditProjectMain.projectLead";
    private static final String CREATE_ID = "id_l.C.EditProjectMain.saveProject";

    private WebElement nameInput;
    private WebElement idInput;
    private WebElement startingNumberInput;
    private WebElement projectLeadSelect;
    private WebElement createButton;

    public ProjectsPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public void redirect(String address) {
        driver.get(address + "createProject");
    }
    
    public void tryToCreateProject(String name, String id, String startingNumber, String lead) {
        linkElements();
        
        nameInput.sendKeys(name);
        idInput.sendKeys(id);
        startingNumberInput.clear();
        startingNumberInput.sendKeys(startingNumber);
        projectLeadSelect.click();
        waitByCss("li[title*='" + lead + "'").click();
        createButton.click();
    }
    
    private void linkElements() {
        nameInput = waitById(NAME_ID);
        idInput = waitById(ID_ID);
        startingNumberInput = waitById(STARTING_NUMBER_ID);
        projectLeadSelect = waitById(PROJECT_LEAD_ID);
        createButton = waitById(CREATE_ID);
    }
}
