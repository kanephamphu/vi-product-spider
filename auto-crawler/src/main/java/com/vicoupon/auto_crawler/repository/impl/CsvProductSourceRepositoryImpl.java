package com.vicoupon.auto_crawler.repository.impl;

import com.vicoupon.auto_crawler.model.ProductPrice;
import com.vicoupon.auto_crawler.repository.CsvProductSourceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("csvProductSourceRepository")
public class CsvProductSourceRepositoryImpl implements CsvProductSourceRepository {
    @Override
    public List<ProductPrice> getAllSources() {
        return List.of();
    }
}
