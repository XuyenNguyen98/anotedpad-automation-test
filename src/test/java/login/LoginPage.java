package login;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    public static void main(String[] args) {
       loginSuccessFully();
       loginFalied();
    }

   static void loginSuccessFully() {
        System.setProperty("webdriver.chrome.driver", "asset/chromedriver.exe");
        WebDriver driver=new ChromeDriver(); driver.manage().window().maximize();
        driver.get("https://anotepad.com/create_account");

        WebElement email=driver.findElement(By.id("loginEmail"));
        WebElement password=driver.findElement(By.xpath(".//div[contains(@class,'col-sm-5')]/input[contains(@placeholder,'Enter Password')]"));
        WebElement login=driver.findElement(By.xpath(".//div[contains(@class,'col-sm-5')]//button[text()='Login']"));

        email.sendKeys("kimxuyennhq@gmail.com");
        password.sendKeys("Test@12345");

        new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")));
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.recaptcha-checkbox-border"))).click();

        login.click();

        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[contains(@class,'container-fluid')]//a[@href='/logout']")));

       driver.close();
    }

    static void loginFalied() {
        System.setProperty("webdriver.chrome.driver", "asset/chromedriver.exe");
        WebDriver driver=new ChromeDriver(); driver.manage().window().maximize();
        driver.get("https://anotepad.com/create_account");

        WebElement email=driver.findElement(By.id("loginEmail"));
        WebElement password=driver.findElement(By.xpath(".//div[contains(@class,'col-sm-5')]/input[contains(@placeholder,'Enter Password')]"));
        WebElement login=driver.findElement(By.xpath(".//div[contains(@class,'col-sm-5')]//button[text()='Login']"));

        email.sendKeys("kimxuyennhq@gmail.com");
        password.sendKeys("invalidTest@12345");

        new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")));
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.recaptcha-checkbox-border"))).click();

        login.click();
        boolean logout= driver.findElement(By.xpath(".//div[contains(@class,'container-fluid')]//a[@href='/logout']")).isDisplayed();

        String actualUrl="https://anotepad.com/create_account";
        String expectedUrl= driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);
        driver.close();
    }
}



