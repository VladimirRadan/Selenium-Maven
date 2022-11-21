package main;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Main2 {


    static WebDriver driver;

    static WebElement getElement(By locator){
        return driver.findElement(locator);
    }

    static void typeIn(By locator, String input){
        getElement(locator).sendKeys(input);
    }

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
        driver = new ChromeDriver();
        Thread.sleep(2000);
        driver.manage().window().maximize();
        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);


        WebElement username = driver.findElement(By.cssSelector("input[type='text']"));

        username.sendKeys("tomsmith");

        WebElement password = driver.findElement(By.cssSelector("input[name='password']"));
        password.sendKeys("SuperSecretPassword!");

        WebElement loginButton = driver.findElement(By.cssSelector(".radius"));
        loginButton.click();

        WebElement loginUserText = driver.findElement(By.xpath("//div[@id='flash']"));
        String actualText = loginUserText.getText();

        String actualColor = loginUserText.getCssValue("background-color");

        String expectedColor = "rgba(93, 165, 35, 1)";

        String []actual = actualText.split("(?<=!)");

        String expectedText = "You logged into a secure area!";

//        if (actual[0].equals(expectedText)){
//            System.out.println("Test Passed");
//        }else {
//            System.out.println("Test Failed");
//        }

        Assert.assertEquals(actual[0], expectedText);
        Assert.assertEquals(actualColor, expectedColor);

        driver.quit();

    }



}
