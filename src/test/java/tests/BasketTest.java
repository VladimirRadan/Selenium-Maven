package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class BasketTest {


    WebDriver driver;

    By headerComputers = By.xpath("//ul[@class='top-menu']//a[contains(text(), 'Computers')]");
    By headerNotebooks = By.xpath("//ul[@class='top-menu']//a[contains(text(), 'Notebooks')]");
    By headerBooks = By.xpath("//ul[@class='top-menu']//a[contains(text(), 'Books')]");
    By addProductToCart = By.cssSelector("input[value='Add to cart']");

    By goToCart = By.cssSelector(".ico-cart .cart-label");
    By listOfProductsInCart = By.cssSelector(".product-subtotal");

    By totalPrice = By.cssSelector(".cart-total-right strong");



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
        String url = "https://demowebshop.tricentis.com/";
        driver.get(url);
    }


    @Test()
    public void addToBasket() throws InterruptedException {
        hover(headerComputers);
        hoverAndClick(headerNotebooks);
        clickOnElement(addProductToCart);
        clickOnElement(headerBooks);
        clickOnElement(addProductToCart);
        clickOnElement(goToCart);

        List<WebElement> productsInCart = getElementList(listOfProductsInCart);
        double sum = 0;
        for (int i = 0; i < productsInCart.size(); i++) {
            sum += Double.parseDouble(productsInCart.get(i).getText());
        }

        double total = Double.parseDouble(getTextFromElement(totalPrice));
        driver.navigate().refresh();
        Assert.assertTrue(sum == total);
        Assert.assertTrue(getElement(totalPrice).isDisplayed());

    }


    @Test()
    public void AddRandomProductFromList() throws InterruptedException {
        clickOnRandomElementFromList(addProductToCart);

    }

//    @AfterMethod
//    public void tearDown(){
//        driver.quit();
//    }


    void typeIn(By locator, String input) {
        getElement(locator).sendKeys(input);
    }

    WebElement getElement(By locator) {
        WebElement element = driver.findElement(locator); // ----->>> Qnjgrjfnegwn45321543 ---->qnhbceu3yf783y4t
        return element;
    }

    List<WebElement> getElementList(By locator) {
        return driver.findElements(locator);
    }

    void clickOnElement(By locator) {
        getElement(locator).click();
    }

    String getTextFromElement(By locator){
        return getElement(locator).getText();
    }

    public void hover(By locator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(getElement(locator))
                .perform();
    }

    public void hoverAndClick(By locator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(getElement(locator))
                .click()
                .perform();
    }

    public void clickOnRandomElementFromList(By locator){
        Random random = new Random();
        List<WebElement> list = driver.findElements(locator);
        int randomElement = random.nextInt(list.size());
        list.get(randomElement).click();
    }





}
