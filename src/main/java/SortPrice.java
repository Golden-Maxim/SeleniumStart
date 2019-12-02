import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Array;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SortPrice {
    public static void checkPrice(WebElement []price){
        for (int i = 0; i < price.length;i++){
            String priceStr = price[i].getText();
            Integer priceValue = Integer.parseInt(priceStr.replace(" ", "").replace("грн", ""));
            if(priceValue > 200) {
                System.out.println(priceValue);
            }
        }
    }

    public static void main(String[] args) {


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

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class ,'price-md')]")));

        WebElement filter = driver.findElement(By.xpath("//*[@id=\"page-search\"]/div[2]/div/div[1]/div[2]/ul/li/select"));
        filter.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"page-search\"]/div[2]/div/div[1]/div[2]/ul/li/select/option[2]")));
        WebElement chooseByPrice = driver.findElement(By.xpath("//*[@id=\"page-search\"]/div[2]/div/div[1]/div[2]/ul/li/select/option[2]"));
        chooseByPrice.click();


        WebElement nextPage;

        for (int i = 1; i < 5;i++ ){

            nextPage = driver.findElement(By.xpath("//*[@id=\"page-search\"]/div[2]/div/div[2]/div[2]/a["+ i +"]"));
            nextPage.click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            WebElement price[] = driver.findElements(By.xpath("//div[span[contains(@class, 'value')]]")).toArray(new WebElement[0]);
            System.out.println("Price from: " + i + " page" );
            checkPrice(price);
        }

    }


}

