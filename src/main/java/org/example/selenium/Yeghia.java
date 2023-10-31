package org.example.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Yeghia {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.youtube.com/");
        WebElement searchInput = driver.findElement(By.cssSelector("input[name='search_query']"));
        Thread.sleep(1000);
        searchInput.sendKeys("National chamber choir of Armenia - Yel, yel");
        Thread.sleep(1000);
        searchInput.sendKeys(Keys.ENTER);

        WebElement linkElement = driver.findElement(By.cssSelector("a[id='video-title']"));
//        WebElement selectVideo = driver.findElement(By.cssSelector("yt-icon-button[aria-label= 'Close player']"));
//        selectVideo.click();

        String url = linkElement.getAttribute("href");
        driver.navigate().to(url);

//        linkElement.click();
    }
}
