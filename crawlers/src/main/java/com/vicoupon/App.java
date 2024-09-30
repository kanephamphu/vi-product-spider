package com.vicoupon;

import com.vicoupon.amazon.AmazonCrawler;
import com.vicoupon.bestbuy.BestBuyCrawler;
import com.vicoupon.common.Crawler;
import com.vicoupon.common.config.CrawlerConfig;
import com.vicoupon.common.models.CrawlerResult;
import com.vicoupon.ebay.EbayCrawler;
import com.vicoupon.walmart.WalmartCrawler;

public class App {
    public static void main( String[] args ) {
        CrawlerConfig config = new CrawlerConfig("https://www.bestbuy.com/site/apple-airpods-4-with-active-noise-cancellation-white/6447385.p?skuId=6447385", 1);
        Crawler crawler = new BestBuyCrawler(config);
        CrawlerResult crawlerResult = crawler.crawl();
    }
}
