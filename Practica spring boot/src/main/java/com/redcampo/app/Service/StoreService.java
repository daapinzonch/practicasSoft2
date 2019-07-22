package com.redcampo.app.Service;

import com.redcampo.app.Entity.Store;
import com.redcampo.app.Repository.StoreRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepo storeRepo;

    public List<Store> findAll(){
        return storeRepo.findAll();
    }

    public Store getStore(Long id){ return storeRepo.findByStoreId(id); }

    public Store createStore(Store store){
        return storeRepo.save(store);
    }

    public Long countStores(Long id) { return storeRepo.countByStoreId(id); }

    public void deleteStore(Store store) { storeRepo.delete(store); }
}
