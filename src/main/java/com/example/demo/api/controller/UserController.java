package com.example.demo.api.controller;

import com.example.demo.api.dto.MyUserDto;
import com.example.demo.entity.AuthRequest;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    @GetMapping("/alluser")
    public ResponseEntity getAllUser () throws Exception {
//        List<User> users = new ArrayList<>();
//        try {
//            users = userService.getAll();
//        }
//        catch (
//                throw new Exception(
//                        ""
//                )
//        )
        return userService.getAll();
    }
    @GetMapping("/one/{name}")
    public ResponseEntity getAllUser (@PathVariable("name") String name) {
        return userService.getUserByName(name);
    }
}
