package homework.pagesKinopoisk;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class KinopoiskMainHWPage extends BaseHWPage {
    private String popUpID = "popup_info";
    private String blockCashElementCss = "#rigth_box_weekend_rus .dl";
    private String searchButtonXpath = "//*[@class = 'header-search-partial-component__button']";
    private String searchFielXpath = "//*[@class = 'header-search-partial-component__search-field']";

    public KinopoiskMainHWPage(WebDriver driver){
        super(driver);
    }

    public void searchInKinopoisk (String searchText){
        WebElement searchField = driver.findElement(By.xpath(searchFielXpath));
        searchField.sendKeys(searchText);
        clickOnElement(By.xpath(searchButtonXpath));
    }

    public void hoverElementInBlockCash(int elementNumber) {
        List<WebElement> blockCashWebElements = driver.findElements(By.cssSelector(blockCashElementCss));
        if (elementNumber <= (blockCashWebElements.size() - 1) && elementNumber>=0) {
            Actions action = new Actions(driver);
            action.moveToElement(blockCashWebElements.get(elementNumber)).build().perform();
        }
        else {
            System.out.println("Wrong element number");
        }

    }

    public void clickOnPopupWithSelectedMovie() {
        WebDriverWait waiter = new WebDriverWait(driver,10);
        WebElement popUp = waiter.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(popUpID))));
        // WebElement popUp2 = driver.findElement(By.id(popUpID)); //эксперимент убрать ожидание
        popUp.click();

    }
}
