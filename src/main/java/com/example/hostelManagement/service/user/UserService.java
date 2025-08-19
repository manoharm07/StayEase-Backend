package com.example.hostelManagement.service.user;


import com.example.hostelManagement.dto.ChangePassDto;
import com.example.hostelManagement.models.user.User;
import com.example.hostelManagement.repository.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    public void createUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public void changePassword(ChangePassDto changePassDto) {
        User user = userRepo.findByEmail(changePassDto.getEmail());
        if (user == null) {
            throw new UsernameNotFoundException("user with this email not found");
        } else if (!encoder.matches(changePassDto.getOldPassword(), user.getPassword())) {
            throw new InputMismatchException("password don't match old password");
        }
        user.setPassword(encoder.encode(changePassDto.getNewPassword()));
        userRepo.save(user);
    }

    public User updateUser(User user) {
        return userRepo.save(user);
    }

    public User findById(Integer id) {
        return userRepo.findById(id).orElseThrow();
    }

    public void deleteUser(User user) {
        userRepo.delete(user);
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public void deleteUserByEmail(String email) {
        userRepo.deleteByEmail(email);
    }
}