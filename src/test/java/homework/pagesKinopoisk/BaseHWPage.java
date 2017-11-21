package homework.pagesKinopoisk;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BaseHWPage {
    protected WebDriver driver;

    public BaseHWPage(WebDriver driver) {
        this.driver = driver;
    }

    protected void clickOnElement(By byselector){
        driver.findElement(byselector).click();
    }
}
