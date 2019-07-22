package com.redcampo.app.Repository;

import com.redcampo.app.Entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepo extends JpaRepository<Store, Long> {
    public List<Store> findAll();

    public Store findByStoreId(Long id);

    public Long countByStoreId(Long id);
}