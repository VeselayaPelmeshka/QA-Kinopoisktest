package homework;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import homework.pagesKinopoisk.KinopoiskMainHWPage;
import homework.pagesKinopoisk.MovieInfoHWPage;
import homework.pagesKinopoisk.SearchResultsHWPage;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class KinopoiskDirBannerTest {

    static  WebDriver chromedriver ;
    String kinopoiskurl = "https://www.kinopoisk.ru";
    KinopoiskMainHWPage kinopoiskMainPage;
    SearchResultsHWPage searchResultsPage;
    MovieInfoHWPage movieInfoPage;

    @Before
    public void setUp(){
        chromedriver = new ChromeDriver();
        chromedriver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);

    }


    // дз 1 проверка на наличие банера и режиссера (не взлелит так как все локаторы в других классах)
    @Test
    public void shouldMoviehaveDirectorandBunner() {
        chromedriver.get(kinopoiskurl);


        String expectedButtonText = "искать!";
//        WebElement searchButton = chromedriver.findElement(By.xpath(searchButtonXpath));
//        String actualButtonText = searchButton.getAttribute("value").trim().toLowerCase();
//        Assert.assertEquals("Текущее значение кнопки = [%s], не совпадает с ожидаемым [%s]", expectedButtonText, actualButtonText);
//
//        String filmNametoSearch = "Годзилла";
//        WebElement searchField = chromedriver.findElement(By.xpath(searchFielXpath));
//        searchField.sendKeys(filmNametoSearch);
//        searchButton.click();
//
//        WebElement searchFilmResult = chromedriver.findElement(By.xpath(searchResultNameXpath));
//        String actualFilmName = searchFilmResult.getText().trim();
//        Assert.assertEquals("Текущее название фильма = [%s], не совпадает с ожидаемым [%s]", filmNametoSearch, actualFilmName);
//
//        searchFilmResult.click();
//        WebElement filmBanner = chromedriver.findElement(By.xpath(bannerXpath));
//        Assert.assertNotEquals("Банер отсутствует", filmBanner.getAttribute("src"), "https://st.kp.yandex.net/images/movies/poster_none.png");
//        WebElement director = chromedriver.findElement(By.xpath(directorXpath));
//        Assert.assertNotEquals("Режиссер отсутствует", director.getText(), "-");

    }

    // дз 2 - тест на проверку банера и режиссера pageObject
    @Test
    public void shouldMoviehaveDirectorandBunnerPageObjMode(){
        String searchMovieTitle = "Индиана джонс 5";
        chromedriver.get(kinopoiskurl);
        kinopoiskMainPage  = new KinopoiskMainHWPage(chromedriver);
        kinopoiskMainPage.searchInKinopoisk(searchMovieTitle);
        searchResultsPage = new SearchResultsHWPage(chromedriver);
        movieInfoPage = searchResultsPage.selectFirstSearchResultsItem();
        //проверка на название фильма
        String actualMovieTitle = movieInfoPage.getMovieTitle();
        assertThat(actualMovieTitle,containsString(searchMovieTitle.toLowerCase()));
        //проверка на банер
        Assert.assertNotEquals("Баннера нет!",movieInfoPage.getBaner().getAttribute("src"),"https://st.kp.yandex.net/images/movies/poster_none.png");
        // проверка на режиссера
        Assert.assertNotEquals("Режиссер отсутствует", movieInfoPage.getDirector().getText(), "-");
    }

    // дз 3 - тест на всплывающий лукап (в методе hoverElementInBlockCash добавлена проверка на ввод корректного номера в списке)
    @Test
    public void shouldVerifiPopupOnHover() {
        chromedriver.get(kinopoiskurl);
        kinopoiskMainPage  = new KinopoiskMainHWPage(chromedriver);//инициализировали страницу
        kinopoiskMainPage.hoverElementInBlockCash(3);
        kinopoiskMainPage.clickOnPopupWithSelectedMovie();

    }

    //дз 4 тест на проверку последнего сиквела к фильму гарри поттер
    @Test
    public void checkLastSequelonMoviePage()  {

        String searchMovieTitle = "Гарри Поттер и филосовский камень";
        chromedriver.get(kinopoiskurl);
        kinopoiskMainPage  = new KinopoiskMainHWPage(chromedriver);
        kinopoiskMainPage.searchInKinopoisk(searchMovieTitle);
        searchResultsPage = new SearchResultsHWPage(chromedriver);
        movieInfoPage = searchResultsPage.selectFirstSearchResultsItem();
        movieInfoPage.lastSequelClick();
    }


    @After
    public void close(){
        chromedriver.close();
    }

    @AfterClass
    public static void afterAll(){
        chromedriver.quit();
    }


}
