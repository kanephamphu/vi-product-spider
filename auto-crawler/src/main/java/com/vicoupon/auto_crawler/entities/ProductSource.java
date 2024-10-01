package com.vicoupon.auto_crawler.entities;

import com.vicoupon.common.constants.CrawlerProvider;
import jakarta.persistence.*;

@Entity
@Table(name = "product_sources")
public class ProductSource {

    @Id
    @Column(name = "id", nullable = false, length = 255)
    private String id;

    @Column(name = "url", nullable = false, length = 255)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(name = "crawler_provider", nullable = false)
    private CrawlerProvider crawlerProvider;

    @Column(name = "system_id", length = 255)
    private String systemId;

    // Constructors
    public ProductSource() {
    }

    public ProductSource(String id, String url, CrawlerProvider crawlerProvider, String systemId) {
        this.id = id;
        this.url = url;
        this.crawlerProvider = crawlerProvider;
        this.systemId = systemId;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }
}