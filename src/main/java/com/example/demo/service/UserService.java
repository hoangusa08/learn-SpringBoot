package com.example.demo.service;

import com.example.demo.api.dto.MyUserDto;
import com.example.demo.entity.Admin;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ResponseEntity createUser(MyUserDto userDto) {
        User TempUser = new User();
        TempUser.setUsername(userDto.getUsername());
        TempUser.setPassword(userDto.getPassword());
        TempUser.setRole(userDto.getRole());
        try {
            userRepository.save(TempUser);
            return ResponseEntity.ok("ok la");
        } catch (Exception e) {
            return ResponseEntity.ok("ngu nhu bo");
        }
    }

}
