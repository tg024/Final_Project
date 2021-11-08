package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SearchPage {
    WebDriver driver;
    public SearchPage(WebDriver driver) {this.driver = driver;}

    By SearchBar = By.id("small-searchterms");
    By SearchButton = By.className("h_search_btn");

    @Step("Click on the search bar")
    public SearchPage ClickSearchBar() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(SearchBar));
        driver.findElement(SearchBar).click();
        return this;
    }
    @Step ("Enter search data")
    public SearchPage SendKeyword(String keyword) {
        driver.findElement(SearchBar).sendKeys(keyword);
        return this;
    }
    @Step ("Click Search button")
    public SearchPage ClickSearchButton() throws InterruptedException {
        driver.findElement(SearchButton).click();
        return this;
    }

    @Step ("Check founded items")
    public SearchPage CheckvalidSearch() throws InterruptedException{
        Assert.assertTrue(driver.findElement(By.className("js-product-items")).isDisplayed());
        return this;
    }
    @Step ("Check Invalid Search")
    public SearchPage CheckInvalidSearch() throws InterruptedException{
        String errorMessage = driver.findElement(By.className("search_result_empty")).getText();
        Assert.assertTrue(errorMessage.contains("0 პროდუქტი"));
        return this;
    }
    
}

