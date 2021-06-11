package com.example.demo.api.controller;

import com.example.demo.api.dto.MyUserDto;
import com.example.demo.entity.AuthRequest;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;
    @PostMapping()
    public ResponseEntity createUser (@RequestBody MyUserDto userDto ) {
        System.out.println(userDto.getUsername());
        return userService.createUser(userDto);
    }
    @GetMapping()
    public String welcome() {
        return "hello";
    }
    @PostMapping("/authenticate")
    public String genereToken( @RequestBody AuthRequest authRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())
            );
        }catch (Exception exception){
            throw new Exception("invalid username/password");
        }
       return jwtUtil.generateToken(authRequest.getUsername());
    }
}
