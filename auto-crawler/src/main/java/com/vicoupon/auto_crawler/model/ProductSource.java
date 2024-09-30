package com.vicoupon.auto_crawler.model;

import com.vicoupon.common.constants.CrawlerProvider;

public class ProductSource {
    private String id;
    private String url;
    private CrawlerProvider crawlerProvider;
    private String systemId;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CrawlerProvider getCrawlerProvider() {
        return crawlerProvider;
    }

    public void setCrawlerProvider(CrawlerProvider crawlerProvider) {
        this.crawlerProvider = crawlerProvider;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }
}
