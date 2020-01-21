import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.http.util.Asserts;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Hotline {

    public static void main(String [] args){


        WebDriverManager.chromedriver().setup();
        WebDriver driver;
        WebElement webElement;
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://hotline.ua/");
        WebElement alert = driver.findElement(By.xpath("/html/body/header/div[1]/div/div/div[1]/div/div[2]/div/div[2]/div/div[2]/span[1]"));
        alert.click();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        WebElement input = driver.findElement(By.xpath("//*[@id=\"searchbox\"]"));
        input.click();
        input.sendKeys("телевизор");

        WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"doSearch\"]"));
        searchButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[contains(@class, 'products-list')]/li[1]/div/p/a")));

        WebElement list = driver.findElement(By.xpath("//ul[contains(@class, 'products-list')]/li[1]/div/p/a"));
        list.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"page-product\"]/div[3]/div/div[2]/div/div[2]/div")));

        WebElement listofparameters = driver.findElement(By.xpath("//ul[contains(@class,'breadcrumbs')]"));
        String str = listofparameters.getText();
        String temp[] = str.trim().split("\\s+");
        for (int i = 0;i < temp.length;i++){

            if(temp[i].contains("Телевизоры")){
                System.out.println("Yes, I made it!!!");
            }
        }
        System.out.println("First lesson with git!!!!");
        System.out.println("Second lesson" +
                " !!!");

        System.out.println("Third commit");
    }
}
