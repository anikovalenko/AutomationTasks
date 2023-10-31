package org.example.selenium.findlessoninw3school;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LessonFinder {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.auto.am/");
        driver.manage().window().maximize();

        Thread.sleep(2000);
        driver.findElement(By.cssSelector("select[class='hf-elem']"));

        WebElement toyotaItem = driver.findElement(By.cssSelector("option[value='386']"));
        toyotaItem.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option[value='2517']")));
        WebElement camryItem = driver.findElement(By.cssSelector("option[value='2517']"));
        camryItem.click();
//        driver.quit();


//        driver.get("https://the-internet.herokuapp.com/");
//        WebElement dropdownPage = driver.findElement(By.cssSelector("a[href='/dropdown']"));
//        dropdownPage.click();
//        Thread.sleep(2000);
//        WebElement dropdown = driver.findElement(By.cssSelector("select[id='dropdown']"));
//        Thread.sleep(2000);
//        WebElement option1 = dropdown.findElement(By.cssSelector("option[value='1']"));
//        option1.click();
    }
}
