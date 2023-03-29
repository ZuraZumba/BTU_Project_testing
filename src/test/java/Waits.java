import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Waits {
    WebDriver driver;

    public Waits() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(2, SECONDS);
    }
    @Test
    public void waitExplicitly() {
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
        driver.manage().window().maximize();
        WebElement submitButton = driver.findElement(By.xpath("//form[@id='input-example']/button[@type='button']"));
        submitButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"input-example\"]/input")));
        WebElement text=driver.findElement(By.id("message"));
        Assert.assertEquals(text.getText(),"It's enabled!");
    }
    @Test
    public void waitImplicitly() {
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
        driver.manage().window().maximize();

        WebElement submitButton = driver.findElement(By.xpath("//form[@id='input-example']/button[@type='button']"));
        submitButton.click();
        WebElement text = driver.findElement(By.xpath("//*[@id=\"message\"]"));
        System.out.println(text.getText());


    }
}
