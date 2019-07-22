package com.redcampo.app.Service;

import com.redcampo.app.Entity.Support.temporalRegister;
import com.redcampo.app.Entity.User;
import com.redcampo.app.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public List<User> findAll(){
        return userRepo.findAll();
    }

    public User getUser(Long id){ return userRepo.findByUserId(id); }

    public User createUser(User user){
        return userRepo.save(user);
    }

    public Long countUsers(Long id) { return userRepo.countByUserId(id); }

    public void deleteUser(User user) { userRepo.delete(user); }

    public User createUserBody(temporalRegister temp){
        User user = new User();
        user.setUserId(temp.getTemporalId());
        user.setPassword(temp.getPassword());
        user.setPhone(temp.getPhone());
        user.setEmail(temp.getEmail());
        user.setLocation(temp.getLocation());
        user.setLastName(temp.getLastName());
        user.setFirtsName(temp.getFirtsName());
        return user;
    }
}
