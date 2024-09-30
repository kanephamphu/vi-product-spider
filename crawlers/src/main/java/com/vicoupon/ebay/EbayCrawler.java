package com.vicoupon.ebay;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.vicoupon.common.AbstractCrawler;
import com.vicoupon.common.config.CrawlerConfig;
import com.vicoupon.common.models.CrawlerResult;
import com.vicoupon.common.utils.CrawlerUtils;

public class EbayCrawler extends AbstractCrawler {
    public EbayCrawler(CrawlerConfig config) {
        super(config);
    }

    @Override
    public CrawlerResult crawl() {
        try {
            // Validate the URL
            if (!validateUrl(config.getTargetUrl())) {
                throw new IllegalArgumentException("Invalid URL: " + config.getTargetUrl());
            }

            log("Starting crawl for eBay at URL: " + config.getTargetUrl());

            // Fetch and parse HTML from the URL
            Document doc = CrawlerUtils.fetchHtml(config.getTargetUrl());

            // Extract product details
            String productName = extractProductName(doc);
            double productPrice = extractPrice(doc);
            String productUrl = config.getTargetUrl();

            log("Crawl complete for eBay");

            return new CrawlerResult(productName, productPrice, productUrl);
        } catch (Exception e) {
            handleError(e);
            return null;
        }
    }

    private String extractProductName(Document document) {
        // Extract product name (example selector, adjust as necessary)
        return document.selectFirst(".x-item-title__mainTitle").text();
    }

    private double extractPrice(Document document) {
        // Select the relevant element for the price
        Element priceElement = document.selectFirst("div.x-price-primary span.ux-textspans");
        
        if (priceElement != null) {
            // Get the text content
            String priceText = priceElement.text();
            
            // Remove currency symbols and spaces
            String price = priceText.replace("US $", "").replace("$", "").trim();
    
            // Parse the price to a double
            return Double.parseDouble(price);
        }
    
        // Return 0.0 if price element is not found
        return 0.0;
    }
}
