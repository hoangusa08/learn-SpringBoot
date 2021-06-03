package com.example.demo.repository;

import com.example.demo.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository  extends JpaRepository<Admin,Integer> {
       Optional<Admin> findById(Integer id);

       @Query(value = "select * from admin",nativeQuery = true)
       List<Admin> findAllAdmin();

       @Query(value = "select * from admin where user_name = :username",nativeQuery = true)
       Admin findByUsername(@Param("username") String name);


}
