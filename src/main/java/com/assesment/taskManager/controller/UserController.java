package com.assesment.taskManager.controller;

import com.assesment.taskManager.dao.UserDao;
import com.assesment.taskManager.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {

@Autowired
private UserDao dao;

@CrossOrigin(origins = "*")
@PostMapping(path = "/login",produces = "application/json",consumes = "application/json")
public HashMap<String,String>login(@RequestBody User u){
    List<User>result = (List<User>) dao.login(u.getEmail(),u.getPassword());
    HashMap<String,String>map=new HashMap<>();
    if(result.size()==0){
        map.put("status","failed");
    }
    else{
        int id = result.get(0).getId();
        map.put("userid",String.valueOf(id));
        map.put("status","success");
    }
    return map;
}

    }
