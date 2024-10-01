package com.vicoupon.amazon;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.vicoupon.common.AbstractCrawler;
import com.vicoupon.common.config.CrawlerConfig;
import com.vicoupon.common.models.CrawlerResult;
import com.vicoupon.common.utils.CrawlerUtils;
import org.jsoup.select.Elements;

public class AmazonCrawler extends AbstractCrawler {
    public AmazonCrawler(CrawlerConfig config) {
        super(config);
    }

    @Override
    public CrawlerResult crawl() {
        try {
            // Validate the URL
            if (!validateUrl(config.getTargetUrl())) {
                throw new IllegalArgumentException("Invalid URL: " + config.getTargetUrl());
            }

            log("Starting crawl for Amazon at URL: " + config.getTargetUrl());

            // Fetch and parse HTML from the URL
            Document doc = CrawlerUtils.fetchHtml(config.getTargetUrl());

            // Extract product details (example)
            String productName = doc.select("#productTitle").text();
            double productPrice = -1;

            try {
                productPrice = extractPrice(doc);
            } catch (Exception exception) {
                productPrice = extractPriceRange(doc)[0];
            }

            if (productPrice == -1 || productPrice == 0.0) {
                throw new Exception("Error when crawling");
            }

            String productUrl = config.getTargetUrl();

            log("Crawl complete for Amazon");

            return new CrawlerResult(productName, productPrice, productUrl);
        } catch (Exception e) {
            System.out.println("Error when crawling: " + config.getTargetUrl());

            return null;
        }
    }

    private double extractPrice(Document document) {
        // Select the relevant elements
        Element priceSymbol = document.selectFirst(".a-price-symbol");
        Element priceWhole = document.selectFirst(".a-price-whole");
        Element priceFraction = document.selectFirst(".a-price-fraction");

        // Construct the full price string
        String price = priceWhole.text() + priceFraction.text();

        return Double.parseDouble(price);
    }

    private double[] extractPriceRange(Document document) {
        // Select the price range element
        Elements priceElements = document.select("span.a-price-range span.a-price");

        if (priceElements.size() == 2) {
            // Get the minimum and maximum prices
            String minPriceText = priceElements.get(0).select("span.a-offscreen").text().replace("$", "").trim();
            String maxPriceText = priceElements.get(1).select("span.a-offscreen").text().replace("$", "").trim();

            // Convert to double
            double minPrice = Double.parseDouble(minPriceText);
            double maxPrice = Double.parseDouble(maxPriceText);

            return new double[]{minPrice, maxPrice}; // Returning as an array [minPrice, maxPrice]
        }

        // Return empty array if price elements are not found
        return new double[]{0.0, 0.0}; // or handle accordingly
    }
}
