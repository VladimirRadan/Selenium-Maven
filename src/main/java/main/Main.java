package main;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {

    static WebDriver driver;

    static WebElement getElement(By locator){
        return driver.findElement(locator);
    }

    static void typeIn(By locator, String input){
        getElement(locator).sendKeys(input);
    }


    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/automation-practice-form");

        typeIn(By.id("firstName"),"Pera");
        typeIn(By.id("lastName"),"Peric");
        typeIn(By.id("userEmail"),"pera@pera.com");
        getElement(By.cssSelector("input[value='Male'] + label")).click();
        typeIn(By.id("userNumber"),"123435345435");
       // getElement(By.id("dateOfBirthInput")).click();

        getElement(By.cssSelector("#state .css-tlfecz-indicatorContainer")).click();
        getElement(By.xpath("//div[text()='Uttar Pradesh']")).click();








    }


}
