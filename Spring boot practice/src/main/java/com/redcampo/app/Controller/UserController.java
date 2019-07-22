package com.redcampo.app.Controller;

import com.redcampo.app.Entity.User;
import com.redcampo.app.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apiusers")
public class UserController {
    @Autowired
    private final UserService usCon;

    @GetMapping
    public String msg(){
        return "USERS AVAIABLE";
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> allStores(){
        return ResponseEntity.ok(usCon.findAll());
    }

    @PostMapping(path ="/insertdata", consumes = "application/json")
    public ResponseEntity<Long> createUser(@RequestBody User user) {
        try{
            Long count=usCon.countUsers(user.getUserId());
            if (count==0) {
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
    public ResponseEntity<Long> deleteUser(@PathVariable(value = "id") Long id) {
        Long count = usCon.countUsers(id);
        try{
            if (count==1) {
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
