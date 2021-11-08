package Zoommer;

import Pages.LoginPage;
import Utils.Rerty;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static DataObject.LoginData.*;

public class LoginTest {
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
    @Description("Login with valid credentials")
    public void ValidLoginTest() throws InterruptedException {
        LoginPage steps = new LoginPage(driver);

        steps
                .GoToLoginPage()
                .FillEmailOrPhone(emailOrPhone)
                .FillPassword(password)
                .ClickLoginButton()
                .ValidLoginCheck();
    }
    @Test(retryAnalyzer = Rerty.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Login with invalid password")
    public void InvalidLoginTest() throws InterruptedException {
        LoginPage steps = new LoginPage(driver);
        steps
                .GoToLoginPage()
                .FillEmailOrPhone(emailOrPhone)
                .FillPassword(invalidPassword)
                .ClickLoginButton()
                .InvalidLoginCheck();
    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
