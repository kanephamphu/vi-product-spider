package com.vicoupon.amazon;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.vicoupon.common.AbstractCrawler;
import com.vicoupon.common.config.CrawlerConfig;
import com.vicoupon.common.models.CrawlerResult;
import com.vicoupon.common.utils.CrawlerUtils;

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
            double productPrice = extractPrice(doc);
            String productUrl = config.getTargetUrl();

            log("Crawl complete for Amazon");

            return new CrawlerResult(productName, productPrice, productUrl);
        } catch (Exception e) {
            handleError(e);
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
}
