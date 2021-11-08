package Pages;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
public class FilterPage {
    WebDriver driver;

    public FilterPage(WebDriver driver) {
        this.driver = driver;
    }

    By navigationButton = By.className("menu_all_link");
    By selectProduct = By.xpath("/html/body/div[7]/section/div[4]/div/div[1]/div[1]/div/a[1]");
    By minPrice = By.xpath("//*[@id=\"min_price\"]");
    By maxPrice = By.xpath("//*[@id=\"max_price\"]");
    By StartFiltering = By.xpath("//*[@id=\"js-filter-form\"]/div[1]/div/div/div/div/button");

    @Step("Open navigation page")
    public FilterPage OpenNavigation() throws InterruptedException {
        WebDriverWait waits1 = new WebDriverWait(driver, 10);
        waits1.until(ExpectedConditions.visibilityOfElementLocated(navigationButton));
        driver.findElement(navigationButton).click();
        return this;
    }

    @Step("Select Product")
    public FilterPage SelectProduct() throws InterruptedException {
        driver.findElement(selectProduct).click();
        Thread.sleep(2000);
        return this;
    }

    @Step("Clear min price")
    public FilterPage ClearMinPrice() throws InterruptedException {
        driver.findElement(minPrice).sendKeys(Keys.BACK_SPACE);
        return this;
    }

    @Step("Enter min price")
    public FilterPage EnterMinPrice(String startPrice) {
        driver.findElement(minPrice).sendKeys(startPrice);
        return this;
    }

    @Step("Clear max price")
    public FilterPage ClearMaxPrice() throws InterruptedException {
        driver.findElement(maxPrice).clear();
        return this;
    }

    @Step("Enter max price")
    public FilterPage EnterMaxPrice(String endPrice) {
        driver.findElement(maxPrice).sendKeys(endPrice);
        return this;
    }

    @Step ("Start filtering process")
    public FilterPage StartFiltering() throws InterruptedException {
        WebDriverWait waits2 = new WebDriverWait(driver, 10);
        waits2.until(ExpectedConditions.visibilityOfElementLocated(StartFiltering));
        driver.findElement(StartFiltering).click();
        return this;
    }
    @Step ("Check Filtered products")
    public FilterPage CheckFilteredProducts() throws InterruptedException {
        WebDriverWait waits2 = new WebDriverWait(driver, 10);
        waits2.until(ExpectedConditions.visibilityOfElementLocated(By.className("product_top_div")));
        Assert.assertTrue(driver.findElement(By.className("")).isDisplayed());
        return this;
    }
    @Step ("Check Invalid Filter")
    public  FilterPage CheckInvalidFilter() throws InterruptedException {
        Assert.assertFalse(driver.findElement(By.className("product_top_div")).isDisplayed());
        return this;
    }
}
