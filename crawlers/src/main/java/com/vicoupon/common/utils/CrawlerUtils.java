package com.vicoupon.common.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class CrawlerUtils {
    public static Document fetchHtml(String url) throws Exception {
        return Jsoup.connect(url).get();
    }

    public static double extractPrice(Document document) {
        // Example: Customize this based on the website's structure
        String priceString = document.select(".price").text();
        return Double.parseDouble(priceString.replace("$", ""));
    }
}
