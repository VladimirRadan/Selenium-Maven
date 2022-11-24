package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class Login {

    private WebDriver driver;


    @BeforeMethod
    @Parameters("browser")
    public void beforeMethodMethod(String browser) throws InterruptedException {
        if (browser.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        }else if(browser.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
        }

        Thread.sleep(3000);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);
    }

    @Test
    @Parameters({"username", "password"})
    public void loginUser(String username, String password){
        WebElement usernameField = driver.findElement(By.cssSelector("input[type='text']"));

        usernameField.sendKeys(username);

        WebElement passwordField = driver.findElement(By.cssSelector("input[name='password']"));
        passwordField.sendKeys(password);

        WebElement loginButton = driver.findElement(By.cssSelector(".radius"));
        loginButton.click();

        WebElement loginUserText = driver.findElement(By.xpath("//div[@id='flash']"));
        String actualText = loginUserText.getText();

        String actualColor = loginUserText.getCssValue("background-color");

        String expectedColor = null;

        if (driver instanceof ChromeDriver) {
            expectedColor = "rgba(93, 164, 35, 1)";
        } else if (driver instanceof FirefoxDriver) {
            expectedColor = "rgb(93, 164, 35)";
        }

        String []actual = actualText.split("(?<=!)");

        String expectedText = "You logged into a secure area!";

//        if (actual[0].equals(expectedText)){
//            System.out.println("Test Passed");
//        }else {
//            System.out.println("Test Failed");
//        }

        Assert.assertEquals(actual[0], expectedText);
        Assert.assertEquals(actualColor, expectedColor);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
