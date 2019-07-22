package com.redcampo.app.Service;

import com.redcampo.app.Entity.Product;
import com.redcampo.app.Repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;

    public List<Product> findAll() { return productRepo.findAll(); }

    public Product getProduct(Long id) { return productRepo.findByProductId(id); }

    public Product create(Product product) {
        return productRepo.save(product);
    }

    public Long countProduct(Long id) {
        return productRepo.countByProductId(id);
    }

    public void delete(Product product) {
        productRepo.delete(product);
    }
}
