package com.redcampo.app.Service;

import com.redcampo.app.Entity.Farmer;
import com.redcampo.app.Repository.FarmerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmerService {
    private final FarmerRepo farmerRepo;

    public List<Farmer> findAll(){ return farmerRepo.findAll(); }

    public Farmer getFarmer(Long id){ return farmerRepo.findByFarmerId(id); }

    public Farmer createFarmer(Farmer farmer){
        return farmerRepo.save(farmer);
    }

    public Long countFarmers(Long id) { return farmerRepo.countByFarmerId(id); }

    public void deleteFarmer(Farmer farmer) { farmerRepo.delete(farmer); }
}
