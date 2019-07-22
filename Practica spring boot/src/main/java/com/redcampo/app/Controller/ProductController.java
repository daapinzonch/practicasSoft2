package com.redcampo.app.Controller;

import com.redcampo.app.Entity.Product;
import com.redcampo.app.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apiproduct")
public class ProductController {
    @Autowired
    private final ProductService prCon;

    @GetMapping
    public String msg(){ return "PRODUCT AVAIABLE"; }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> allProducts(){
        return ResponseEntity.ok(prCon.findAll());
    }

    @PostMapping(path ="/insertdata", consumes = "application/json")
    public ResponseEntity<Long> createProduct(@RequestBody Product product) {
        try{
            Long count=prCon.countProduct(product.getProductId());
            if (count==0) {
                prCon.create(product);
                return new ResponseEntity<>(count, HttpStatus.CREATED);
            }else {
                return new ResponseEntity<>(count,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>((long) -1,HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/deletedata/{id}")
    public ResponseEntity<Long> deleteProduct(@PathVariable(value = "id") Long id) {
        Long count = prCon.countProduct(id);
        try{
            if (count==1) {
                prCon.delete(prCon.getProduct(id));
                return new ResponseEntity<>(count, HttpStatus.ACCEPTED);
            }else {
                return new ResponseEntity<>(count, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e) {
            return new ResponseEntity<>((long)-1,HttpStatus.FORBIDDEN);
        }
    }
}
