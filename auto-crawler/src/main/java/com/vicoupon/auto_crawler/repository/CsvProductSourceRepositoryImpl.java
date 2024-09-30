package com.vicoupon.auto_crawler.repository;

import com.vicoupon.auto_crawler.model.ProductSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("csvProductSourceRepository")
public class CsvProductSourceRepositoryImpl implements ProductSourceRepository {
    @Override
    public List<ProductSource> getAllSources() {
        return List.of();
    }
}
