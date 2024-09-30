package com.vicoupon.adidas;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.vicoupon.common.AbstractCrawler;
import com.vicoupon.common.config.CrawlerConfig;
import com.vicoupon.common.models.CrawlerResult;
import com.vicoupon.common.utils.CrawlerUtils;

public class AdidasCrawler extends AbstractCrawler {
    public AdidasCrawler(CrawlerConfig config) {
        super(config);
    }

    @Override
    public CrawlerResult crawl() {
        try {
            // Validate the URL
            if (!validateUrl(config.getTargetUrl())) {
                throw new IllegalArgumentException("Invalid URL: " + config.getTargetUrl());
            }

            log("Starting crawl for Adidas at URL: " + config.getTargetUrl());

            // Fetch and parse HTML from the URL
            Document doc = CrawlerUtils.fetchHtml(config.getTargetUrl());

            // Extract product details
            String productName = extractProductName(doc);
            double productPrice = extractPrice(doc);
            String productUrl = config.getTargetUrl();

            log("Crawl complete for Adidas");

            return new CrawlerResult(productName, productPrice, productUrl);
        } catch (Exception e) {
            handleError(e);
            return null;
        }
    }

    private String extractProductName(Document document) {
        Element titleElement = document.selectFirst("h1.product-title"); // Adjust selector as needed
        return titleElement != null ? titleElement.text() : "Title not found";
    }

    private double extractPrice(Document document) {
        // Select the relevant element for the price
        Element priceElement = document.selectFirst("div.price"); // Adjust selector as needed
        
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
