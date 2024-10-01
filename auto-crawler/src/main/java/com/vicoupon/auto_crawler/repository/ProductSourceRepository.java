package com.vicoupon.auto_crawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vicoupon.auto_crawler.entities.ProductSource;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSourceRepository extends JpaRepository<ProductSource, String> {
}
