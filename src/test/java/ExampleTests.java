import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ExampleTests {
    WebDriver driver;

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }


    @Test
    public void firstTest(){
        driver.get("http://the-internet.herokuapp.com/");
        driver.manage().window().maximize();
        WebElement button=driver.findElement(By.xpath("//ul/li/a"));
        button.click();
        WebElement text = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3"));
        Assert.assertEquals(text.getText(),"No Test");
    }
    @Test
    public void locatorTest(){
        driver.get("http://tutorialsninja.com/demo/index.php?route=product/category&path=33");
        driver.manage().window().maximize();
        //*[@id="content"]/div/div[2]/h1 - XPath
        //#content > div > div.col-sm-4 > h1 - css
        List<WebElement> anchors = driver.findElements(By.className("product-thumb"));
        Assert.assertEquals(anchors.size(),4,"Check count");
         WebElement button=driver.findElement(By.partialLinkText("Canon EOS 5D"));
        button.click();
    }
    @Test
    public void dropdownTest(){
        driver.get("http://the-internet.herokuapp.com/dropdown");
        driver.manage().window().maximize();
        WebElement element=driver.findElement(By.id("dropdown"));
        Select select = new Select(element);
        select.selectByIndex(1);
    }
    @Test
    public void checkBoxTest(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
         driver = new ChromeDriver(options);
        driver.get("http://the-internet.herokuapp.com/checkboxes");
        driver.manage().window().maximize();
        WebElement element=driver.findElement(By.xpath("//form[@id='checkboxes']/input[2]"));
        element.click();

    }
    @Test
    public void browserCommands(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://tutorialsninja.com/demo/index.php?route=product/category&path=33");
        // Get the title of the page
        String title = driver.getTitle();
        System.out.println("Title " + title);
        // Get the current URL
        String url = driver.getCurrentUrl();
        System.out.println("URL " + url);
        // Get the current page HTML source
        String html = driver.getPageSource();
        System.out.println("HTML " + html);
    }
    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
