package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.junit.IJUnitTestRunner;

public class BasketPage {
    WebDriver driver;

    public BasketPage(WebDriver driver) {
        this.driver = driver;
    }
    By AccpetCookies = By.className("agree-btn");
    By chooseCategory = By.xpath("/html/body/div[7]/section/div[2]/div[1]/div[1]/div[2]/ul/li[5]/a");
    By CheckSubCategory = By.className("accordion_checkbox_container");
    By SelectProduct = By.xpath("//*[@id=\"js-filter-cont\"]/div[4]/div[2]/div/div[1]/div[1]/div/div[1]/a/h4");
    By AddToBasket = By.xpath("//*[@id=\"pd-r-content\"]/div[1]/div[2]/button[2]");
    By RemoveProduct = By.className("fas fa-times js-remove-cart-item little_product_delete");

    @Step ("Accept on Cookies")
    public BasketPage AcceptCookies() throws InterruptedException {
        WebDriverWait waits = new WebDriverWait(driver, 10);
        waits.until(ExpectedConditions.visibilityOfElementLocated(AccpetCookies));
        driver.findElement(AccpetCookies).click();
        return this;
    }

    @Step("Choose category")
    public BasketPage ChooseCategory() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(chooseCategory));
        driver.findElement(chooseCategory).click();
        return this;
    }

    @Step("Choose Subcategory")
    public BasketPage ChooseSubCategory() throws InterruptedException {
        driver.findElement(CheckSubCategory).click();
        return this;
    }

    @Step("Choose Product")
    public BasketPage ChooseProduct() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(SelectProduct).click();
        return this;
    }

    @Step("Add to basket")
    public BasketPage AddToBasket() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"product-details-form\"]/div[2]")));
        if (driver.findElement(By.xpath("//*[@id=\"n-prod-additional\"]/div/div")).isDisplayed()) {
            driver.findElement(AddToBasket).click();
        }
        return this;
    }

    @Step("Check Added Product")
    public BasketPage CheckAddedProduct() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart_total")));
        Assert.assertTrue(driver.findElement(By.className("cart_total_products")).isDisplayed());
        return this;
    }
}
