package com.vicoupon.auto_crawler.model;

import com.vicoupon.common.constants.CrawlerProvider;

public class ProductPrice {
    private String id;
    private String url;
    private CrawlerProvider crawlerProvider;
    private double price;
    private String systemId;

    // Private constructor to enforce object creation through the Builder
    private ProductPrice(Builder builder) {
        this.id = builder.id;
        this.url = builder.url;
        this.crawlerProvider = builder.crawlerProvider;
        this.price = builder.price;
        this.systemId = builder.systemId;
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Builder class
    public static class Builder {
        private String id;
        private String url;
        private CrawlerProvider crawlerProvider;
        private double price;
        private String systemId;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setCrawlerProvider(CrawlerProvider crawlerProvider) {
            this.crawlerProvider = crawlerProvider;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setSystemId(String systemId) {
            this.systemId = systemId;
            return this;
        }

        public ProductPrice build() {
            return new ProductPrice(this);
        }
    }
}
