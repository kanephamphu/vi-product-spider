package com.vicoupon;

import com.vicoupon.amazon.AmazonCrawler;
import com.vicoupon.common.Crawler;
import com.vicoupon.common.config.CrawlerConfig;
import com.vicoupon.common.models.CrawlerResult;
import com.vicoupon.ebay.EbayCrawler;
import com.vicoupon.walmart.WalmartCrawler;

public class App {
    public static void main( String[] args ) {
        CrawlerConfig config = new CrawlerConfig("https://www.walmart.com/ip/159670044", 1);
        Crawler crawler = new WalmartCrawler(config);
        CrawlerResult crawlerResult = crawler.crawl();
    }
}
