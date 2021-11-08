package Zoommer;

import Pages.FilterPage;
import Pages.LoginPage;
import Utils.Rerty;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static DataObject.FilterData.*;

public class FilterTest {
    WebDriver driver;

    @BeforeMethod(description = "config")
    public void setUp() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://zoommer.ge/");
        Thread.sleep(3000);
    }
    @Test(retryAnalyzer = Rerty.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Filter")
    public void TestFilter() throws InterruptedException{
        FilterPage steps = new FilterPage(driver);
        steps
                .OpenNavigation()
                .SelectProduct()
                .ClearMinPrice()
                .EnterMinPrice(startPrice)
                .ClearMaxPrice()
                .EnterMaxPrice(endPrice)
                .StartFiltering()
                .CheckFilteredProducts();
    }
    @Test(retryAnalyzer = Rerty.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Filter")
    public void InvalidFilterTest() throws InterruptedException {
        FilterPage step = new FilterPage(driver);
        step
                .OpenNavigation()
                .SelectProduct()
                .ClearMinPrice()
                .ClearMaxPrice()
                .EnterMinPrice(invalidStartPrice)
                .EnterMaxPrice(invalidEndPrice)
                .StartFiltering()
                .CheckInvalidFilter();
    }
    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
