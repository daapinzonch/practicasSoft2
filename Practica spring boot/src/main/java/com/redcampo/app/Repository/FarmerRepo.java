package com.redcampo.app.Repository;

import com.redcampo.app.Entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FarmerRepo extends JpaRepository<Farmer, Integer> {
    public List<Farmer> findAll();

    public Farmer findByFarmerId(Long id);

    public Long countByFarmerId(Long id);
}
