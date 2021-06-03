package com.example.demo.service;

import com.example.demo.api.dto.Dto;
import com.example.demo.api.dto.UpdateAdmin;
import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transaction;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Transactional
    public Page<Admin> findPage(Pageable pageable){
        return adminRepository.findAllAdmin();
    }
    @Transactional
    public ResponseEntity getOne(Integer id) {
        Optional<Admin> admin = adminRepository.findById(id);
        Admin ad= new Admin();
        ad=admin.get();
        return ResponseEntity.ok(ad);
    }
    @Transactional
    public ResponseEntity getAllAdmin() {
        List<Admin> admins=new ArrayList<>();
        admins=adminRepository.findAllAdmin();
        return ResponseEntity.ok(admins);
    }

    @Transactional
    public ResponseEntity getOneByUsername(String name) {
       Admin admin = adminRepository.findByUsername(name);
        return ResponseEntity.ok(new Dto(admin.getUser_name(),admin.getPass_word()));
    }

    @Transactional
    public ResponseEntity updateAdmin(Integer id, Dto update) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        Admin Admintemp = new Admin();
        Admintemp = optionalAdmin.get();
        Admintemp.setUser_name(update.getName());
        Admintemp.setPass_word(update.getPass());
        try {
            adminRepository.save(Admintemp);
            return ResponseEntity.ok("ok la");
        } catch (Exception e) {
            return ResponseEntity.ok("ngu nhu bo");
        }
    }

    @Transactional
    public ResponseEntity createAdmin(UpdateAdmin admin) {
        Admin Admintemp = new Admin();
        Admintemp.setUser_name(admin.getUser_name());
        Admintemp.setPass_word(admin.getPass_word());
        try {
            adminRepository.save(Admintemp);
            return ResponseEntity.ok("ok la");
        } catch (Exception e) {
            return ResponseEntity.ok("ngu nhu bo");
        }
    }

    @Transactional
    public ResponseEntity deleteAdmin(Integer id) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        try {
            adminRepository.delete(optionalAdmin.get());
            return ResponseEntity.ok("ok la");
        } catch (Exception e) {
            return ResponseEntity.ok("ngu nhu bo");
        }
    }
}
