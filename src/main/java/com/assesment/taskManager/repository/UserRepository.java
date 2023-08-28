package com.assesment.taskManager.repository;

import com.assesment.taskManager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {


    @Query(value = "SELECT * FROM `user` WHERE `email` =:email AND `password` =:password",nativeQuery = true)
    List<User> login(@Param("email")String email, @Param("password")String password);



}
