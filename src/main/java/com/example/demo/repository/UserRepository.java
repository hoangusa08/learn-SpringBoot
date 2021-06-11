package com.example.demo.repository;

import com.example.demo.entity.Admin;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "select * from MyUser",nativeQuery = true)
    List<User> findAllUser();

    User findByUsername(String username);
}
