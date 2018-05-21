import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.elements.Issue;
import utils.pages.CreateIssuePage;
import utils.pages.IssuesPage;
import utils.pages.LoginPage;
import utils.pages.ProjectsPage;

import static junit.framework.TestCase.assertTrue;

public class YouTrackTest {

    private static WebDriver driver;
    private static WebDriverWait driverWait;

    private static final String LONG_STRING = "But I must explain to you how all this mistaken idea of " +
            "denouncing pleasure and praising pain was born and I will give you a complete account of the system, " +
            "and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. " +
            "No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do " +
            "not know how to pursue pleasure rationally encounter consequences that are extremely painful. " +
            "Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain," +
            " but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. " +
            "To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage " +
            "from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying " +
            "consequences, or one who avoids a pain that produces no resultant pleasure?";
    private static final long DELAY_SEC = 5;
    private static final String BASE_URL = "http://localhost:8080/";

    @BeforeClass
    public static void init() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        driverWait = new WebDriverWait(driver, DELAY_SEC);

        LoginPage loginPage = new LoginPage(driver, driverWait);
        loginPage.redirect(BASE_URL);
        loginPage.logIn("root", "root");

        ProjectsPage projectsPage = new ProjectsPage(driver, driverWait);
        projectsPage.redirect(BASE_URL);
        projectsPage.tryToCreateProject("testProject", "test", "1", "root");
    }


    @Test
    public void noDescription() {
        testIssuePositive("No description", "");
    }

    @Test
    public void shortDescription() {
        testIssuePositive("Short description", "Description");
    }

    @Test
    public void longDescription() {
        testIssuePositive("Long description", LONG_STRING);
    }

    @Test
    public void chineseDescription() {
        testIssuePositive("Chinese symbols description", "網站有中、英文版本，也有繁、簡體版，可通過每頁左上角的連結隨時調整");
    }

    @Test
    public void longSummary() {
        testIssuePositive(LONG_STRING, "Description");
    }

    @Test
    public void chineseSummary() {
        testIssuePositive("網站有中、英文版本，也有繁、簡體版，可通過每頁左上角的連結隨時調整", "Description");
    }

    @Test
    public void noSummaryError() {
        testIssueNegative("", "Description");
    }

    private void testIssuePositive(String summary, String description) {
        Issue issue = new Issue(summary + System.currentTimeMillis(),
                description + System.currentTimeMillis());
        CreateIssuePage createIssue = new CreateIssuePage(driver, driverWait);
        createIssue.redirect(BASE_URL);
        createIssue.create(issue);

        IssuesPage issues = new IssuesPage(driver, driverWait);
        issues.redirect(BASE_URL);
        assertTrue(issues.getIssues().contains(issue.shortVersion()));
    }

    private void testIssueNegative(String summary, String description) {
        Issue issue = new Issue(summary, description);
        CreateIssuePage createIssue = new CreateIssuePage(driver, driverWait);
        createIssue.redirect(BASE_URL);
        createIssue.createWithError(issue);
    }

    @AfterClass
    public static void finish() {
        driver.quit();
    }
}
