package com.vicoupon.common.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class CrawlerUtils {
    public static Document fetchHtml(String url) throws Exception {
        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36 vicoupon.combot";

        return Jsoup.connect(url)
            .userAgent(userAgent)
            .get();
    }

    public static double extractPrice(Document document) {
        // Example: Customize this based on the website's structure
        String priceString = document.select(".price").text();
        return Double.parseDouble(priceString.replace("$", ""));
    }
}
