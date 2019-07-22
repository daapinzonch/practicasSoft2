package com.redcampo.app.Repository;

import com.redcampo.app.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    public List<Product> findAll();

    public Product findByProductId(Long id);

    public Long countByProductId(Long id);
}
