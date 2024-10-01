package com.vicoupon.auto_crawler;

import com.vicoupon.auto_crawler.service.ProductSourceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AutoCrawlerCommandLine implements CommandLineRunner {
    private final ProductSourceService productSourceService;

    public AutoCrawlerCommandLine(ProductSourceService productSourceService) {
        this.productSourceService = productSourceService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Command-line arguments received:");

        productSourceService.crawlProductPrices();
    }
}
