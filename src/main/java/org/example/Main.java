package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static final String PHONE_REGEX = "^[0-9+\\(\\)\\s-]+$";
    static final String EMAIL_REGEX = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
//        static final String LINK_REGEX = "^https:\\/\\/[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,}(:[0-9]{1,5})?(\\/\\S*)?$\n";
//    static final String LINK_REGEX = "(https?|ftp)://[^\\\\s/$.?#].[^\\\\s]*";
    static final String LINK_REGEX = "https://[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,}(:[0-9]{1,5})?(\\/\\S*)?";


    public static void main(String[] args) throws InterruptedException {
        Object monitor = new Object();

        WebDriver chrome = new ChromeDriver();
        searchKeyword(chrome);
        String chromeURL = chrome.getCurrentUrl();
        String chromeWindowHandle = chrome.getWindowHandle();
        synchronized (monitor) {
            Thread.currentThread().wait(2000);
        }
//        WebElement link = chrome.findElement(By.className("qLRx3b"));
        List<WebElement> siteElements = chrome.findElements(By.tagName("cite"));
        List<String> urls = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            System.out.println();
            WebElement currentElement = siteElements.get(i);
            String linkText = currentElement.getText();

            Pattern pattern = Pattern.compile(LINK_REGEX);
            Matcher matcher = pattern.matcher(linkText);
            if(matcher.find()){
                String url = matcher.group();
                urls.add(url);
            }
        }
        System.out.println("urls -> " + urls);







//        System.out.println(link.getText());
//        link.click();

//        for (int i = 0; i < 10; i++) {
//
//            String windowHandle = chrome.getWindowHandle();
//            WebDriver newTab = chrome.switchTo().window(windowHandle);
//            findContactsInFooter(newTab);
////            newTab.navigate().to(chromeURL);
////            newTab.switchTo().window(chromeWindowHandle);
//        }


//        findContactPage(newTab);
//        findContacts(newTab);

//        WebElement footer = newTab.findElement(By.tagName("footer"));
//        recursivelyFind(footer);


        System.out.println("end");


//        WebElement elementsWithText = newTab.findElement(By.xpath("//p[contains(text(),'1-888-473-8050')]"));
//        System.out.println(elementsWithText.getText());
//        System.out.println(elementsWithText.getTagName());
//        phone = newTab.findElement(By.xpath("//strong[contains(text(),'Phone:')]"));
//        System.out.println(phone.getText());

//        String innerHTML = phone.getAttribute("innerHTML");
//        System.out.println(innerHTML);
//        System.out.println(phone.getDomProperty("innerHTML"));


    }

    private static void findContactsInFooter(WebDriver newTab) {

        List<WebElement> buttons = newTab.findElements(By.tagName("a"));
        for (WebElement button : buttons) {
            if (button.getText().contains("Accept")) {
                button.click();
            }
        }

        WebElement footer = newTab.findElement(By.tagName("footer"));
        List<WebElement> links = footer.findElements(By.tagName("a"));
        Pattern phonePattern = Pattern.compile(PHONE_REGEX);
        Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
        for (WebElement link : links) {
            Matcher phoneMatcher = phonePattern.matcher(link.getText());
            Matcher emailMatcher = emailPattern.matcher(link.getText());
            if (phoneMatcher.find()) {
                System.out.println("~~~~~YAHOOOOOO~~~~");
                System.out.println(link.getText());
            } else if (emailMatcher.find()) {
                System.out.println("~~~~~YAHOOOOOO~~~~");
                System.out.println(link.getText());
            }
        }


//        System.out.println("\n \n footer -> div -> a");
//        List<WebElement> divs = footer.findElements(By.tagName("div"));
//        for (WebElement div : divs) {
//            List<WebElement> texts = div.findElements(By.tagName("a"));
//            for (WebElement text : texts) {
//                System.out.println(text.getText());
//            }
//    }


//        WebElement element = footer.findElement(By.xpath("//a"));
//        System.out.println(element.getAttribute("href"));


//        WebElement div = footer.findElement(By.tagName("div"));
//        List<WebElement> links = div.findElements(By.tagName("a"));
//        for (WebElement link : links) {
//            try {
//                System.out.println(link.getText());
//            } catch (NoSuchElementException e) {
//                continue;
//            }
//        }
    }

//    static List<WebElement> recursivelyFind(WebElement element) {
//        List<WebElement> texts = element.findElements(By.xpath("//*[contains(text(), 't:')]"));
//        if (texts.size() > 0) {
//            return texts;
//        }
//        List<WebElement> divs = element.findElements(By.tagName("div"));
//        divs.stream().forEach((div) -> recursivelyFind(div));
//
//    }

    private static void findContacts(WebDriver chrome) {
        String windowHandle = chrome.getWindowHandle();
        WebDriver newTab = chrome.switchTo().window(windowHandle);
        WebElement phone = newTab.findElement(By.linkText("Phone"));
        System.out.println(phone.getText());
        System.out.println(phone.getTagName());
        System.out.println(phone.getLocation());


        WebElement paragraph = newTab.findElement(By.tagName("p"));

        WebDriverWait wait = new WebDriverWait(newTab, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeSelected(paragraph)));
        System.out.println(paragraph.getText());
    }


//        List<WebElement> texts = newTab.findElements(By.tagName("p"));
//        for (WebElement text : texts) {
//            System.out.println(text.getText());
//        }
//        System.out.println("\n\n\n\n");

//        List<WebElement> divs = newTab.findElements(By.tagName("aside"));
//        System.out.println(divs.size());
//        for (WebElement div : divs) {
//            List<WebElement> ps = div.findElements(By.tagName("p"));
//            System.out.println(ps.size());
//            for (WebElement p : ps) {
//                System.out.println(p.getText());
//            }
//        }


//        String regex = "^(?=.*\\d)[0-9\\-]+$";
//        String anyDigit = "\\\\b\\\\D*?(\\\\d+)\\\\D*?\\\\b";
//        Pattern pattern = Pattern.compile(regex);
//        Pattern patternAnyDigit = Pattern.compile(anyDigit);
//        for (WebElement text : texts) {

//            String[] splits = text.getText().split(regex);
//            System.out.println(Arrays.toString(splits) + "/n");

//            Matcher matcherAnyDigit = patternAnyDigit.matcher(text.getText());
//            while (matcherAnyDigit.find()) {
//                System.out.println(text.getText());
//            }

//            Matcher matcher = pattern.matcher(text.getText());
//            while (matcher.find()) {
//                System.out.println(text.getText());
//            }


//        try {
//            WebElement aside = newTab.findElement(By.tagName("aside"));
//            WebElement p = aside.findElement(By.tagName("p"));
//            System.out.println(p.getText());
//        } catch (NoSuchElementException e) {
//            List<WebElement> divs = newTab.findElements(By.tagName("div"));
//            for (WebElement a : divs) {
//                WebElement p = a.findElement(By.tagName("p"));
//                System.out.println(p.getText());
//            }
//        }


    private static void findContactPage(WebDriver newTab) {
        //        WebElement contact = newTab.findElement(By.xpath("//a[@href, contains('Contact')]"));
        WebElement footer = newTab.findElement(By.tagName("footer"));
        WebElement contact = footer.findElement(By.linkText("Contact Us"));
        contact.click();
    }


    private static void searchKeyword(WebDriver chrome) {
        chrome.get("https://www.google.com");
        WebElement searchArea = chrome.findElement(By.className("gLFyf"));
        searchArea.sendKeys("websites with phone and email");
        WebElement div = chrome.findElement(By.className("FPdoLc"));
        WebElement button = div.findElement(By.className("gNO89b"));
        button.click();
    }


}
