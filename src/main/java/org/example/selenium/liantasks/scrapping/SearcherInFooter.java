package org.example.selenium.liantasks.scrapping;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearcherInFooter {

    void method(WebDriver driver, int pageIndex) {
        System.out.println("******************************************************");
        try {
            WebElement footerByTag = driver.findElement(By.tagName("footer"));
            searchContacts(footerByTag, pageIndex);
        } catch (NoSuchElementException e) {
            try {
                WebElement footerByClass = driver.findElement
                        (By.xpath("//*[contains(@class, 'footer') or contains(@id, 'footer')]"));
                searchContacts(footerByClass, pageIndex);
            } catch (NoSuchElementException exc) {
                WebElement contactUsPage = driver.findElement(By.xpath("//a[contains(@href, 'contact')]"));
                contactUsPage.click();
                searchContacts(contactUsPage, pageIndex);
            }
        }
    }

    private void searchContacts(WebElement elementContainingContacts, int pageIndex) {
        List<WebElement> phones = elementContainingContacts.findElements(By.xpath("//a[contains(@href, 'tel:')]"));
        List<WebElement> emails = elementContainingContacts.findElements(By.xpath("//a[contains(@href, 'mailto:')]"));
        for (WebElement phone : phones) {
            System.out.println("phone: " + pageIndex + phone.getText());
        }
        for (WebElement email : emails) {
            System.out.println("email: " + pageIndex + email.getText());
        }
    }

}
