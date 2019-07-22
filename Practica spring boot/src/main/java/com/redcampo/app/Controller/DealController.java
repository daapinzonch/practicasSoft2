package com.redcampo.app.Controller;

import com.redcampo.app.Entity.Deal;
import com.redcampo.app.Entity.Product;
import com.redcampo.app.Entity.Store;
import com.redcampo.app.Entity.Support.temporalOrder;
import com.redcampo.app.Service.DealService;
import com.redcampo.app.Service.ProductService;
import com.redcampo.app.Service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apideal")
public class DealController {
    @Autowired
    private final DealService deCon;
    @Autowired
    private final StoreService stCon;
    @Autowired
    private final ProductService prCon;

    @GetMapping()
    public String msg(){
        return "DEALS AVAIABLE";
    }

    @GetMapping("/deals")
    public ResponseEntity<List<Deal>> allDeals(){ return ResponseEntity.ok(deCon.findAll()); }

    @GetMapping("/deals/{status}")
    public ResponseEntity<List<Deal>> statusList(@PathVariable(value = "status") boolean st){
        return ResponseEntity.ok(deCon.statusList(st));
    }

    @GetMapping("/deals/{id}")
    public ResponseEntity<List<Deal>> idList(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(deCon.dealIdList(id));
    }

    @PostMapping(path = "/insertdata", consumes = "application/json")
    public ResponseEntity<String> createDeal(@RequestBody temporalOrder temp){
        Deal deal = new Deal();
        try{
            if(stCon.countStores(temp.getStoreKey())==1 && prCon.countProduct(temp.getProductKey())==1){
                Product product = prCon.getProduct(temp.getProductKey());
                Store store = stCon.getStore(temp.getStoreKey());
                deal.setDealId((long) 322);
                deal.setDealPrice(temp.getQuantity()*product.getProductPrice());
                deal.setDealQuantity(temp.getQuantity());
                deal.setStatus(false);
                deal.setProduct(product);
                deal.setStore(store);
                deCon.create(deal);
                return new ResponseEntity<>(temp.getStoreKey()+" "+stCon.countStores(temp.getStoreKey())+temp.getProductKey()+" "+prCon.countProduct(temp.getProductKey()), HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(temp.getStoreKey()+" "+stCon.countStores(temp.getStoreKey())+temp.getProductKey()+" "+prCon.countProduct(temp.getProductKey()), HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(temp.getStoreKey()+" "+stCon.countStores(temp.getStoreKey())+temp.getProductKey()+" "+prCon.countProduct(temp.getProductKey()), HttpStatus.FORBIDDEN);
        }
    }
}
