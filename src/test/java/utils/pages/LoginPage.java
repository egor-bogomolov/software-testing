package utils.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasicPage {

    private static final String LOGIN_ID = "id_l.L.login";
    private static final String PASSWORD_ID = "id_l.L.password" ;
    private static final String LOG_IN_BUTTON_ID = "id_l.L.loginButton";

    private WebElement loginInput;
    private WebElement passwordInput;
    private WebElement logInButton;

    public LoginPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public void redirect(String address) {
        driver.get(address + "login");
    }

    public void logIn(String login, String password) {
        linkElements();

        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);
        logInButton.click();
    }

    private void linkElements() {
        loginInput = waitById(LOGIN_ID);
        passwordInput = waitById(PASSWORD_ID);
        logInButton = waitById(LOG_IN_BUTTON_ID);
    }
}
