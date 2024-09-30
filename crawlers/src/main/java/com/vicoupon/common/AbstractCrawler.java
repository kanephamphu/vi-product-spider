package com.vicoupon.common;

import com.vicoupon.common.config.CrawlerConfig;
import com.vicoupon.common.exceptions.CrawlerException;
import com.vicoupon.common.models.CrawlerResult;

public abstract class AbstractCrawler implements Crawler {
    protected CrawlerConfig config;

    public AbstractCrawler(CrawlerConfig config) {
        this.config = config;
    }

    // Abstract method that each specific crawler must implement
    public abstract CrawlerResult crawl();

    // Common method to be used by all crawlers, e.g., to validate the URL
    protected boolean validateUrl(String url) {
        // Add URL validation logic here
        return url != null && url.startsWith("http");
    }

    // Common method to log the crawler activity
    protected void log(String message) {
        System.out.println("[Crawler Log] " + message);
    }

    // Common error handling for crawlers
    protected void handleError(Exception e) {
        throw new CrawlerException("Error during crawl", e);
    }
}
