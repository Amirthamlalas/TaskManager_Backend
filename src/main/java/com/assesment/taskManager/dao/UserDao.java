package com.assesment.taskManager.dao;

import com.assesment.taskManager.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDao extends CrudRepository<User,Integer> {


    @Query(value = "SELECT * FROM `user` WHERE `email` =:email AND `password` =:password",nativeQuery = true)
    List<User> login(@Param("email")String email, @Param("password")String password);
}
