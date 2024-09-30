package com.vicoupon.walmart;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.vicoupon.common.AbstractCrawler;
import com.vicoupon.common.config.CrawlerConfig;
import com.vicoupon.common.models.CrawlerResult;
import com.vicoupon.common.utils.CrawlerUtils;

public class WalmartCrawler extends AbstractCrawler {
    public WalmartCrawler(CrawlerConfig config) {
        super(config);
    }

    @Override
    public CrawlerResult crawl() {
        try {
            // Validate the URL
            if (!validateUrl(config.getTargetUrl())) {
                throw new IllegalArgumentException("Invalid URL: " + config.getTargetUrl());
            }

            log("Starting crawl for Walmart at URL: " + config.getTargetUrl());

            // Fetch and parse HTML from the URL
            Document doc = CrawlerUtils.fetchHtml(config.getTargetUrl());

            // Extract product details
            String productName = extractProductName(doc);
            double productPrice = extractPrice(doc);
            String productUrl = config.getTargetUrl();

            log("Crawl complete for Walmart");

            return new CrawlerResult(productName, productPrice, productUrl);
        } catch (Exception e) {
            handleError(e);
            return null;
        }
    }

    private String extractProductName(Document document) {
        Element titleElement = document.selectFirst("#main-title"); // Adjust selector as needed
        return titleElement != null ? titleElement.text() : "Title not found";
    }

    private double extractPrice(Document document) {
        // Select the span element that contains the price
        Element priceElement = document.selectFirst("span[itemprop=price]");

        if (priceElement != null) {
            // Get the price text
            String priceText = priceElement.text();

            // Remove the currency symbol (in this case, $) and trim any extra spaces
            String priceWithoutCurrency = priceText.replace("$", "").trim();

            // Convert the price to a double
            return Double.parseDouble(priceWithoutCurrency);
        }

        // Return 0.0 if price element is not found
        return 0.0;
    }
}
