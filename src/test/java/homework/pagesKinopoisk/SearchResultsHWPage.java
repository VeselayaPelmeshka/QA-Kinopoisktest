package homework.pagesKinopoisk;

import lesson.pages.MovieInfoPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultsHWPage extends BaseHWPage {

    private String firstSearchResultsElementId ="FlappImg_0";

    public SearchResultsHWPage(WebDriver driver){
        super(driver);
    }

    public MovieInfoHWPage selectFirstSearchResultsItem(){
        clickOnElement(By.id(firstSearchResultsElementId));
        return new MovieInfoHWPage(driver);
    }
}
