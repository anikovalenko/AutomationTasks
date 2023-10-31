package org.example.selenium.liantasks.scrapping;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

import java.util.List;

public class PageOpeningExecutor {
    private static int pageIndex = 0;

    void openLink(WebDriver driver, List<String> urls) {
        SearcherInFooter searcherInFooter = new SearcherInFooter();
        for (String url : urls) {
            driver.switchTo().newWindow(WindowType.TAB);
            driver.get(url);
            System.out.println();
            System.out.println(url);
            searcherInFooter.method(driver, pageIndex);
            pageIndex++;
        }
    }
}
