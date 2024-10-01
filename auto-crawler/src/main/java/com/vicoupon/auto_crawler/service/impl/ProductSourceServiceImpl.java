package com.vicoupon.auto_crawler.service.impl;

import com.vicoupon.auto_crawler.entities.ProductSource;
import com.vicoupon.auto_crawler.model.ProductPrice;
import com.vicoupon.auto_crawler.repository.ProductSourceRepository;
import com.vicoupon.auto_crawler.service.ProductSourceService;
import com.vicoupon.common.Crawler;
import com.vicoupon.common.CrawlerFactory;
import com.vicoupon.common.models.CrawlerResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductSourceServiceImpl implements ProductSourceService {
    private final ProductSourceRepository productSourceRepository;

    public ProductSourceServiceImpl(ProductSourceRepository productSourceRepository) {
        this.productSourceRepository = productSourceRepository;
    }

    @Override
    public void crawlProductPrices() {
        List<ProductSource> productSources = this.productSourceRepository.findAll();
        List<ProductPrice> productPrices = productSources.parallelStream()
                .map(productSource -> {
                    Crawler crawler = CrawlerFactory.create(productSource.getUrl(), productSource.getCrawlerProvider());

                    return toProductPrice(productSource, crawler.crawl());
                })
                .filter(Objects::nonNull)
                .toList();
    }


    private ProductPrice toProductPrice(ProductSource productSource, CrawlerResult crawlerResult) {
        return new ProductPrice
                .Builder()
                .setId(productSource.getId())
                .setPrice(crawlerResult.getProductPrice())
                .setCrawlerProvider(productSource.getCrawlerProvider())
                .setUrl(productSource.getUrl())
                .setSystemId(productSource.getSystemId())
                .build();
    }

}
