package com.vicoupon.common.config;

public class CrawlerConfig {
    private String targetUrl;
    private int retryAttempts;

    public CrawlerConfig(String targetUrl, int retryAttempts) {
        this.targetUrl = targetUrl;
        this.retryAttempts = retryAttempts;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public int getRetryAttempts() {
        return retryAttempts;
    }
}
