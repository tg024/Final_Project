package Zoommer;

import Pages.SearchPage;
import Utils.Rerty;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static DataObject.SearchData.*;
public class SearchTest {
    WebDriver driver;

    @BeforeMethod(description = "Configure browser before tests")
    public void setUp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://zoommer.ge/");
        Thread.sleep(3000);
    }
    @Test(retryAnalyzer = Rerty.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Search with valid data")

    public void ValidSearch() throws InterruptedException{
        SearchPage steps = new SearchPage(driver);
        steps
                .ClickSearchBar()
                .SendKeyword(ValidSearchData)
                .ClickSearchButton()
                .CheckvalidSearch();
    }
    @Test(retryAnalyzer = Rerty.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Search with invalid data")

    public void  InvalidSearch() throws InterruptedException{
        SearchPage steps = new SearchPage(driver);
        steps
                .ClickSearchBar()
                .SendKeyword(InvalidSearchData)
                .ClickSearchButton()
                .CheckInvalidSearch();
    }
    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
