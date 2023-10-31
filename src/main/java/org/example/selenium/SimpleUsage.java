package org.example.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class SimpleUsage {

    public static void main(String[] args) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized", "--incognito");
        //chromeOptions.addArguments("--window-size=1920,1080");

        WebDriverManager driverManager = WebDriverManager.chromedriver();
        driverManager.setup();
        //webDriverManager - bonigarcia library, which solves the exception caused by web driver versions' differences

        //chromeOptions.addArguments("--headless");
        // -- header allows to execute the test without opening the browser visually. It's actually the same, but it saves RAM
        WebDriver driver = new ChromeDriver();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long seconds = driver.manage().timeouts().getPageLoadTimeout().getSeconds();
        System.out.println(seconds);

        WebDriver.Timeouts timeouts = driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));

        //implicit  wait = element not found within some period
    }
}
