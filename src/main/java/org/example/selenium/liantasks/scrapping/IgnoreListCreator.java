package org.example.selenium.liantasks.scrapping;

import java.util.ArrayList;
import java.util.List;

public class IgnoreListCreator {
    private static List<String> ignoreList;

    static {
        ignoreList = new ArrayList<>();
        ignoreList.add("https://support.keepandshare.com");
        ignoreList.add("https://www.quora.com");
        ignoreList.add("https://www.texau.com");
        ignoreList.add("https://www.freepik.com");
        ignoreList.add("https://www.uplead.com");
        ignoreList.add("https://github.com");
        ignoreList.add("https://support.apple.com");

    }

    static List<String> getIgnoreList() {
        return ignoreList;
    }

}
