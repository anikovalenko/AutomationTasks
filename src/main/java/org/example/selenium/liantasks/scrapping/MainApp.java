package org.example.selenium.liantasks.scrapping;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        LinkFinderInGoogle linkFinder = new LinkFinderInGoogle();
        List<String> searchResultsList = linkFinder.getSearchResultsList();
        WebDriver driver = linkFinder.getDriver();

        PageOpeningExecutor pageOpeningExecutor = new PageOpeningExecutor();
        pageOpeningExecutor.openLink(driver, searchResultsList);
    }
}
