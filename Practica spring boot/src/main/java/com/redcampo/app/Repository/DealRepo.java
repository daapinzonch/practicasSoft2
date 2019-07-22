package com.redcampo.app.Repository;

import com.redcampo.app.Entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealRepo extends JpaRepository<Deal, Long> {
    public List<Deal> findAll();

    public List<Deal> findByDealStatus(boolean status);

    public List<Deal> findByDealId(Long id);

    public Deal getByDealId(Long id);
}
