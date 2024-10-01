package com.vicoupon.auto_crawler.service.impl;

import com.vicoupon.auto_crawler.entities.ProductSource;
import com.vicoupon.auto_crawler.model.ProductPrice;
import com.vicoupon.auto_crawler.repository.ProductSourceRepository;
import com.vicoupon.auto_crawler.service.CsvExportService;
import com.vicoupon.auto_crawler.service.ProductSourceService;
import com.vicoupon.common.Crawler;
import com.vicoupon.common.CrawlerFactory;
import com.vicoupon.common.constants.CrawlerProvider;
import com.vicoupon.common.models.CrawlerResult;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;

@Service
public class ProductSourceServiceImpl implements ProductSourceService {
    private final ProductSourceRepository productSourceRepository;
    private final CsvExportService csvExportService;

    public ProductSourceServiceImpl(ProductSourceRepository productSourceRepository,
                                    CsvExportService csvExportService) {
        this.productSourceRepository = productSourceRepository;
        this.csvExportService = csvExportService;
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

        this.csvExportService.exportProducePrices(productPrices, "produce_price.csv");
    }


    private ProductPrice toProductPrice(ProductSource productSource, CrawlerResult crawlerResult) {
        if (ObjectUtils.isEmpty(crawlerResult)) {
            return null;
        }

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
