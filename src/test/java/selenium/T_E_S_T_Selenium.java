package selenium;

import static java.lang.Thread.sleep;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class T_E_S_T_Selenium
{
    @Test
    public void testSelenium() throws Exception
    {
        System.out.println("testSelenium");
        
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(false);
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver(chromeOptions);
        
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setHeadless(false);
        System.setProperty("webdriver.firefox.driver", "firefoxdriver.exe");
        //WebDriver driver = new FirefoxDriver(firefoxOptions);
                
        driver.get( "https://www.google.com/" );
        
        driver.findElement(By.cssSelector("#lst-ib")).sendKeys("Hest");
        driver.findElement(By.cssSelector("#lst-ib")).sendKeys(Keys.RETURN);
        
        sleep(1000);
        
        driver.findElement(By.cssSelector("#lu_map")).click();
        
        sleep(1000);
        
        List<WebElement> divs = driver.findElements(By.cssSelector(".dbg0pd div"));
        String text = divs.get(2).getText();
         
        assertEquals("Foreningen Hestens Værn", text);
    }
}
