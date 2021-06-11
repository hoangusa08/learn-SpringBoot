package com.example.demo.api.controller;

import com.example.demo.api.dto.Dto;
import com.example.demo.api.dto.UpdateAdmin;
import com.example.demo.service.AdminService;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin")
public class AdminApi {
    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity loginDto (@RequestBody UpdateAdmin dto ) {
        System.out.println(dto.getPass_word());
        return adminService.loginAdmin(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneApi(@PathVariable("id") Integer id) {
        return adminService.getOne(id);
    }
    @GetMapping("/all")
    public ResponseEntity getAllApi() {
        return adminService.getAllAdmin();
    }
    @GetMapping("/user/{username}")
    public ResponseEntity getOneByUsername(@PathVariable("username") String username) {
        return adminService.getOneByUsername(username);
    }
    @GetMapping("/abc")
    public String getOneByUsername() {
        return "abc";
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateAdminById(@PathVariable("id") Integer id, @RequestBody Dto update) {
        return adminService.updateAdmin(id,update);
    }

    @PostMapping("/post")
    public ResponseEntity createBrandDto (@RequestBody UpdateAdmin dto ) {
        return adminService.createAdmin(dto);
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity createBrandDto (@PathVariable("id") Integer id , @RequestBody Dto dto ) {
        return adminService.updateAdmin(id , dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBrandDto (@PathVariable("id") Integer id ) {
        return adminService.deleteAdmin(id);
    }

}
