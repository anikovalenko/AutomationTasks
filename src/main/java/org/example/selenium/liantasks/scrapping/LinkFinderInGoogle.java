package org.example.selenium.liantasks.scrapping;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class LinkFinderInGoogle {
    private WebDriver driver;
    private List<String> uris = new ArrayList<>();

    List<String> getSearchResultsList() {
        organizeGettingLinks();
        return uris;
    }

    private void organizeGettingLinks() {
        driver = new ChromeDriver();
        driver.get("https://www.google.com");
        driver.manage().window().maximize();
        searchKeyword();
        findLinks();
        System.out.println("uris: " + uris);
    }

    private void searchKeyword() {
        WebElement searchArea = driver.findElement(By.cssSelector("textarea[id='APjFqb']"));
        searchArea.sendKeys("websites with phone and email");
        searchArea.sendKeys(Keys.ENTER);
    }

    private void findLinks() {
//        Actions action = new Actions(driver);
//        action.sendKeys(Keys.PAGE_DOWN).perform();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, 5000);");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        List<WebElement> linkElements =
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector("span[jscontroller='msmzHf'] a[jsname='UWckNb']")));
        System.out.println("Size before removing: " + linkElements.size());
        linkElements = removingUnnecessaryLinks(linkElements);
        System.out.println("Size after removing: " + linkElements.size());
        int maxSize = linkElements.size() > 10 ? 10 : linkElements.size();
        for (int i = 0; i < maxSize; i++) {
            WebElement currentLinkElement = linkElements.get(i);
            String href = currentLinkElement.getAttribute("href");
            parseToURI(href);
        }
    }

    private static List<WebElement> removingUnnecessaryLinks(List<WebElement> linkElements) {
        List<String> ignoreLinksList = IgnoreListCreator.getIgnoreList();
        List<WebElement> validList = new ArrayList<>();
        for (WebElement linkElement : linkElements) {
            boolean contains = false;
            String href = linkElement.getAttribute("href");
            for (String ignoreLink : ignoreLinksList) {
                if (href.contains(ignoreLink)) {
                    contains = true;
                }
            }
            if (!contains) {
                validList.add(linkElement);
            }
        }

//        List<WebElement> newLinkElements = linkElements
//                .stream()
//                .filter((linkElement) -> {
//                    String href = linkElement.getAttribute("href");
//                    return !ignoreLinksList.contains(href);
//                })
//                .collect(Collectors.toList());


//        List<Integer> indexToRemove = new ArrayList<>();
//        Iterator iterator = linkElements.iterator();
//        while (iterator.hasNext()) {
//            WebElement linkElement = (WebElement) iterator.next();
////        for (WebElement linkElement : linkElements) {
//            Iterator iteratorOnNegative = ignoreLinksList.iterator();
//            while (iteratorOnNegative.hasNext()) {
//                String negativeLink = (String) iteratorOnNegative.next();
//                String href = linkElement.getAttribute("href");
//                if (href.contains(negativeLink)) {
//                    linkElements.remove(linkElement);
//                }
//            }

//                for (String negativeLink : ignoreLinksList) {
//                    String href = linkElement.getAttribute("href");
//                    if (href.contains(negativeLink)) {
////                    int index = linkElements.indexOf(linkElement);
//                        linkElements.remove(linkElement);
//                    }
//                }
//        }
        return validList;
    }

    void parseToURI(String link) {
        try {
            URL url = new URL(link);
            String protocol = url.getProtocol();
            if (protocol.equals("https")) {
                String host = url.getHost();
                String baseURI = protocol + "://" + host;
                uris.add(baseURI);
            } else {
                //skip logic
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
