package org.example.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.groovy.json.internal.Chr;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.security.Key;
import java.util.Iterator;
import java.util.List;

public class UiElementFinder {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/");
//        testCheckboxesPage(driver);
//        testAddRemoveElements(driver);
//        testBasicAuthPage(driver);
//        testBrokenImagesPage(driver);
//        testChallengingDomPage(driver);

    }




    private static void testChallengingDomPage(WebDriver driver){
        WebElement challengingDomPage = driver.findElement(By.xpath("//a[@href='/challenging_dom']"));
        challengingDomPage.click();
        WebElement elementFoundByXPath = driver.findElement(By.xpath("//tr[3]/td[5]"));
        System.out.println("Found By XPath: " + elementFoundByXPath.getText());
        WebElement elementFoundByCss = driver.findElement(By.cssSelector("tr:nth-child(3)>td:nth-child(5)"));
        System.out.println("Found By Css: " + elementFoundByCss.getText());
        driver.navigate().back();
    }

    private static void testBrokenImagesPage(WebDriver driver) {
        WebElement brokenImagesPage = driver.findElement(By.xpath("//a[@href='/broken_images']"));
        brokenImagesPage.click();
        List<WebElement> imgs = driver.findElements(By.tagName("img"));
        Iterator iterator = imgs.iterator();
        while (iterator.hasNext()) {
            WebElement next = (WebElement) iterator.next();
            String naturalWidth = next.getAttribute("naturalWidth");
            if (naturalWidth != null && !naturalWidth.isEmpty() && !naturalWidth.equals("0")) {
                System.out.println("image displayed successfully");
            } else {
                System.out.println("failed load the image");
            }
        }
        driver.navigate().back();
    }

    private static void testBasicAuthPage(WebDriver driver) {
        WebElement basicAuthPage = driver.findElement(By.xpath("//a[@href='/basic_auth']"));
        basicAuthPage.click();

    }

    private static void testAddRemoveElements(WebDriver driver) throws InterruptedException {
        Thread.sleep(2000);
        WebElement addRemovePage = driver.findElement(By.xpath("//a[@href='/add_remove_elements/']"));
        addRemovePage.click();
        WebElement addElementButton = driver.findElement(By.xpath("//button[@onClick='addElement()']"));
        for (int i = 0; i < 5; i++) {
            addElementButton.click();
        }
        Thread.sleep(2000);
//        WebElement deleteButton = driver.findElement(By.cssSelector("button[class='added-manually']:nth-child(3)"));
        WebElement deleteButton = driver.findElement(By.xpath("//button[@class='added-manually'][3]"));
        System.out.println(deleteButton.getCssValue("color"));
        String script = "arguments[0].style.setProperty('background-color', 'red');";
        ((JavascriptExecutor) driver).executeScript(script, deleteButton);
        Thread.sleep(2000);
        deleteButton.click();
        driver.navigate().back();
    }

    private static void testCheckboxesPage(WebDriver driver) {
        WebElement checkboxLink = driver.findElement(By.xpath("//a[@href='/checkboxes']"));
//        WebElement checkboxLink = driver.findElement(By.cssSelector("a[href='/checkboxes']"));
        checkboxLink.click();
//        WebElement checkbox1 = driver.findElement(By.xpath("//form/input[contains(@value,' checkbox 1')]"));
        WebElement checkbox1 = driver.findElement(By.cssSelector("input[type='checkbox']:first-child"));
        if (!checkbox1.isSelected()) {
            checkbox1.click();
            System.out.println("in if block");
        }
        WebElement checkbox2 = driver.findElement(By.cssSelector("input[type='checkbox']:first-child"));
        if (checkbox2.isSelected()) {
            checkbox2.click();
        }
        driver.navigate().back();
    }
}
