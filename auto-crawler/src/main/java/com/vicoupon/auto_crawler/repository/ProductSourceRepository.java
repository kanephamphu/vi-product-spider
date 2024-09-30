package com.vicoupon.auto_crawler.repository;

import com.vicoupon.auto_crawler.model.ProductSource;

import java.util.List;

public interface ProductSourceRepository {
    List<ProductSource> getAllSources();
}
