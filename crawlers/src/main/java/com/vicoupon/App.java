package com.vicoupon;

import com.vicoupon.amazon.AmazonCrawler;
import com.vicoupon.common.Crawler;
import com.vicoupon.common.config.CrawlerConfig;

public class App {
    public static void main( String[] args ) {
        CrawlerConfig config = new CrawlerConfig("https://www.amazon.com/Razer-BlackShark-V2-Gaming-Headset/dp/B086PKMZ21", 1);
        Crawler crawler = new AmazonCrawler(config);

        crawler.crawl();
    }
}
