package com.vicoupon.auto_crawler.service;

import com.vicoupon.auto_crawler.model.ProductPrice;

import java.util.List;

public interface CsvExportService {
    void exportProducePrices(List<ProductPrice> productPrices, String fileName);
}
