import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.User;

public class TestUsingDataProvidersFiles {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver",
                "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://verstandqa.com/login-employee-v2/");
        wait = new WebDriverWait(driver, 10);
    }

    @Test(dataProvider = "getAuthenticationDataFromExcel", dataProviderClass = DataProviders.class)
    public void invalidLoginUsingExcel(String usuario, String password){
        driver.findElement(By.id("user")).sendKeys(usuario);
        driver.findElement(By.id("pass")).sendKeys(password);
        driver.findElement(By.id("loginButton")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("errorMessage")));
        Assert.assertTrue(driver.findElement(By.name("errorMessage")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.name("errorMessage")).getText().contains("Bad credentials"));
    }

    @Test(dataProvider = "getAuthenticationDataFromCSV", dataProviderClass = DataProviders.class)
    public void invalidLoginUsingCSV(User user){
        driver.findElement(By.id("user")).sendKeys(user.getUsuario());
        driver.findElement(By.id("pass")).sendKeys(user.getPassword());
        driver.findElement(By.id("loginButton")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("errorMessage")));
        Assert.assertTrue(driver.findElement(By.name("errorMessage")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.name("errorMessage")).getText().contains("Bad credentials"));
    }


    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
