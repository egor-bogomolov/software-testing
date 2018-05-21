package utils.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class BasicPage {

    WebDriver driver;
    WebDriverWait driverWait;

    BasicPage(WebDriver driver, WebDriverWait driverWait) {
        this.driver = driver;
        this.driverWait = driverWait;
    }

    WebElement waitById(String id) {
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
    }

    WebElement waitByCss(String css) {
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(css)));
    }
}
