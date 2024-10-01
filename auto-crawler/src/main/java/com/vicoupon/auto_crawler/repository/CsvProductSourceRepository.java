package com.vicoupon.auto_crawler.repository;

import com.vicoupon.auto_crawler.model.ProductPrice;

import java.util.List;

public interface CsvProductSourceRepository {
    List<ProductPrice> getAllSources();
}
