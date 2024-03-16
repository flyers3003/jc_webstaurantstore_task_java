package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import pages.BasePage;
import pages.HomePage;

public class BaseTests {
    private WebDriver driver;
    protected BasePage basePage;

    @BeforeClass
    public void setupClass(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeTest
    public void setupTest(){
        driver = new ChromeDriver();
        driver.get("https://www.webstaurantstore.com/");
        driver.manage().window().setSize(new Dimension(1600,1200));
        basePage = new BasePage(driver);
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}
