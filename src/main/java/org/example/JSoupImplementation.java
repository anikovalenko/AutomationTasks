package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class JSoupImplementation {
    static final String PHONE_REGEX = "^[0-9+\\(\\)\\s-]+$";
    static final String EMAIL_FOUND_MESSAGE = "Email is found: ";
    static final String PHONE_FOUND_MESSAGE = "Phone is found: ";
    static final String ERROR_MESSAGE = "No contacts in contact page";

    public static void main(String[] args) {
        String query = "websites with phone and email";

        try {
            Document doc = Jsoup.connect("https://www.google.com/search?q=" + query).get();
            Elements results = doc.select("h3");

            for (int i = 0; i < 10; i++) {
                Element result = results.get(i);
                String currentUrl = result.parent().attr("href");

                System.out.println("Link: " + currentUrl);
                if (currentUrl.isEmpty() || currentUrl.isBlank()) {
                    System.out.println("The link is empty, can't enter.");
                    continue;
                }
                Document newPage = Jsoup.connect(currentUrl).get();
                Elements footerLinks = newPage.select(".footer a");

                for (Element linkElement : footerLinks) {
                    boolean includesContactPage = searchPhoneAndEmail(linkElement);
                    if (includesContactPage) {
                        String pathToContactPage = linkElement.attr("href");
                        String baseURI = getBaseURI(currentUrl);
                        Document contactPage = switchToContactPage(pathToContactPage, baseURI);
                        Elements linkElementsInContactPage = getLinkElements(contactPage);
                        for (Element link : linkElementsInContactPage) {
                            searchPhoneAndEmail(link);
                        }
                    }
                }
                System.out.println("--------------------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Elements getLinkElements(Document page) {
        Elements linkElements = page.select("a");
        Elements footerLinkElements = page.select(".footer a");
        linkElements.removeAll(footerLinkElements);
        return linkElements;
    }

    private static Document switchToContactPage(String pathToContactPage, String baseURI) throws IOException {
        Document contactPage;
        if (pathToContactPage.contains("www")) {
            contactPage = Jsoup.connect(pathToContactPage).get();
        } else {
            contactPage = Jsoup.connect(baseURI.concat(pathToContactPage)).get();
        }
        return contactPage;
    }

    private static String getBaseURI(String currentUrl) throws MalformedURLException {
        URL fullUrl = new URL(currentUrl);
        String protocol = fullUrl.getProtocol();
        String host = fullUrl.getHost();
        String baseURI = protocol.concat("://").concat(host);
        return baseURI;
    }

    private static boolean searchPhoneAndEmail(Element linkElement) {
        String href = linkElement.attr("href");
        if (href.contains("tel:")) {
            System.out.println(PHONE_FOUND_MESSAGE + href);
        } else if (href.contains("mailto:")) {
            System.out.println(EMAIL_FOUND_MESSAGE + href);
        } else return href.contains("contact");
        return false;
    }
}
