package Zoommer;
import Pages.BasketPage;
import Pages.LoginPage;
import Utils.Rerty;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.naming.InsufficientResourcesException;

public class BasketTest {
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
    @Description("Add product to the basket")
    public void AddToBasket() throws InterruptedException {
        BasketPage steps = new BasketPage(driver);
        steps
                .AcceptCookies()
                .ChooseCategory()
                .ChooseSubCategory()
                .ChooseProduct()
                .AddToBasket()
                .CheckAddedProduct();
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
