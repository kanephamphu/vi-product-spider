package com.vicoupon.common;

import com.vicoupon.amazon.AmazonCrawler;
import com.vicoupon.common.config.CrawlerConfig;
import com.vicoupon.common.constants.CrawlerProvider;
import com.vicoupon.ebay.EbayCrawler;
import com.vicoupon.walmart.WalmartCrawler;

public class CrawlerFactory {
    public Crawler create(String url, CrawlerProvider crawlerProvider) {
        CrawlerConfig config = new CrawlerConfig(url, 0);

        return switch (crawlerProvider) {
            case AMAZON -> new AmazonCrawler(config);
            case EBAY -> new EbayCrawler(config);
            case WALMART -> new WalmartCrawler(config);
            default -> null;
        };
    }
}
