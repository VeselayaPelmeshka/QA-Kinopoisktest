package homework.pagesKinopoisk;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;

public class MovieInfoHWPage extends BaseHWPage{
    private String sequelMovieNameXpath = "";
    private String currentSequelMovieBannerXpath = "(//*[@class='right-slider sequel_scroller']//img)[last()]";
    private String rightScrollBannerXpath = "//*[@class='right-slider sequel_scroller']//*[contains(@class,\"arrows_left  right_go_button\")]";
    private String movieTitleByClass = "moviename-big";
    private String bannerByXpath = "//*[@id = 'photoBlock']//img";
    private String directorXpath = "//*[text()=\"режиссер\"]//following-sibling::*";


    public MovieInfoHWPage(WebDriver driver) {
        super(driver);
    }

    public String getMovieTitle(){
        WebElement movieTitle = driver.findElement(By.className(movieTitleByClass));
        return movieTitle.getText().toLowerCase();
    }

    public WebElement getBaner(){
        WebElement movieBanner = driver.findElement(By.xpath(bannerByXpath));

        return movieBanner;
    }

    public WebElement getDirector(){
        WebElement director = driver.findElement(By.xpath(directorXpath));
        return director;
    }

    public void lastSequelClick()  {
        WebElement rightScrolBanner = driver.findElement(By.xpath(rightScrollBannerXpath));
        while(!rightScrolBanner.getAttribute("class").toLowerCase().contains("disable")){
            rightScrolBanner.click();

        }

        WebDriverWait waiter = new WebDriverWait(driver,3);
        WebElement currentSequelBanner = waiter.until(ExpectedConditions.elementToBeClickable(By.xpath(currentSequelMovieBannerXpath)));
        currentSequelBanner.click();
    }


}
