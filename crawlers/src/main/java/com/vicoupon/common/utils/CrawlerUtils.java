package com.vicoupon.common.utils;

import org.jsoup.Jsoup;

public class CrawlerUtils {
    public static Document fetchHtml(String url) throws Exception {
        return Jsoup.connect(url).get();
    }
}
