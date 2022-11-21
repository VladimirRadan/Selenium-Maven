package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class RegisterTest {


    WebDriver driver;

    void typeIn(By locator, String input){
        getElement(locator).sendKeys(input);
    }

     WebElement getElement(By locator){
        return driver.findElement(locator);
    }

    @BeforeMethod
    public void beforeMethodMethod(){
        System.out.println("Before method!");
    }


    @Test()
    public void registerUser(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://magento.softwaretestingboard.com/");
        //getElement("").click();
        typeIn(By.id("firstname"), "John");
        typeIn(By.id("lastname"), "Smith");
        typeIn(By.id("email_address"), randomize(6)+ "@test.com");
        typeIn(By.id("password"), "John");
        typeIn(By.id("password-confirmation"), "John");


        List<WebElement>list = driver.findElements(By.xpath("//*[contains(text(),'Sign In')]"));
        list.get(0).click();
    }

    @Test()
    public void registerUser2(){
        System.out.println("test 2 ");
    }

    @Test()
    public void registerUser3(){
        System.out.println("test 3 ");
    }

    @Test()
    public void registerUser4(){
        System.out.println("test 4 ");
    }


    public String randomize(int length){
        String [] chars = {"a","e","v","t","h","b","y"};
        String result = "";
        for (int i = 0; i < length; i++) {
            Random random = new Random();
            int index = random.nextInt(chars.length);
            result = result + chars[index];
        }
        return result;
    }


}
