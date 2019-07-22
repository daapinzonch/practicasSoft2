package com.redcampo.app.Controller;

import com.redcampo.app.Entity.Store;
import com.redcampo.app.Entity.Support.temporalRegister;
import com.redcampo.app.Entity.User;
import com.redcampo.app.Service.StoreService;
import com.redcampo.app.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apistores")
public class StoreController {
    @Autowired
    private final StoreService stCon;
    @Autowired
    private final UserService usCon;

    @GetMapping
    public String msg(){
        return "STORE AVAIABLE";
    }

    @GetMapping("/stores")
    public ResponseEntity<List<Store>> allStores(){
        return ResponseEntity.ok(stCon.findAll());
    }

    @PostMapping(path ="/insertdata", consumes = "application/json")
    public ResponseEntity<Long> createStore(@RequestBody temporalRegister temp) {
        try{
            Long count=stCon.countStores(temp.getTemporalId());
            if (count==0) {
                User user = usCon.createUserBody(temp);
                Store store = new Store();
                store.setStoreId(temp.getTemporalId());
                user.setStore(store);
                store.setUser(user);
                store.setStoreId(user.getUserId());
                stCon.createStore(store);
                usCon.createUser(user);
                return new ResponseEntity<>(count, HttpStatus.CREATED);
            }else {
                return new ResponseEntity<>(count,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>((long) -1,HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/deletedata/{id}")
    public ResponseEntity<Long> deleteStore(@PathVariable(value = "id") Long id) {
        Long count = stCon.countStores(id)+usCon.countUsers(id);
        try{
            if (count==2) {
                usCon.deleteUser(usCon.getUser(id));
                return new ResponseEntity<>(count, HttpStatus.ACCEPTED);
            }else {
                return new ResponseEntity<>(count, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e) {
            return new ResponseEntity<>((long)-1,HttpStatus.FORBIDDEN);
        }
    }
}
