package com.vicoupon.bestbuy;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.vicoupon.common.AbstractCrawler;
import com.vicoupon.common.config.CrawlerConfig;
import com.vicoupon.common.models.CrawlerResult;
import com.vicoupon.common.utils.CrawlerUtils;

public class BestBuyCrawler extends AbstractCrawler {
    public BestBuyCrawler(CrawlerConfig config) {
        super(config);
    }

    @Override
    public CrawlerResult crawl() {
        try {
            // Validate the URL
            if (!validateUrl(config.getTargetUrl())) {
                throw new IllegalArgumentException("Invalid URL: " + config.getTargetUrl());
            }

            log("Starting crawl for Best Buy at URL: " + config.getTargetUrl());

            // Fetch and parse HTML from the URL
            Document doc = CrawlerUtils.fetchHtml(config.getTargetUrl());

            // Extract product details
            String productName = extractProductName(doc);
            double productPrice = extractPrice(doc);
            String productUrl = config.getTargetUrl();

            log("Crawl complete for Best Buy");

            return new CrawlerResult(productName, productPrice, productUrl);
        } catch (Exception e) {
            handleError(e);
            return null;
        }
    }

    private String extractProductName(Document document) {
        // Select the product name element
        Element titleElement = document.selectFirst("div.sku-title h1.heading-4"); // Updated selector
        return titleElement != null ? titleElement.text() : "Title not found";
    }
    
    private double extractPrice(Document document) {
        // Select the price element
        Element priceElement = document.selectFirst("div.priceView-hero-price span[aria-hidden=true]");
    
        if (priceElement != null) {
            // Get the price text
            String priceText = priceElement.text();
    
            // Remove the currency symbol ($) and trim any extra spaces
            String priceWithoutCurrency = priceText.replace("$", "").trim();
    
            // Convert the price to a double
            return Double.parseDouble(priceWithoutCurrency);
        }
    
        // Return 0.0 if price element is not found
        return 0.0;
    }
    
}
