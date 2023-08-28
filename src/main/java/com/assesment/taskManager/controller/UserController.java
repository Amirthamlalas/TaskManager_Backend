package com.assesment.taskManager.controller;

import com.assesment.taskManager.model.User;
import com.assesment.taskManager.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {


    @Autowired
    private UserServiceImpl userService;


@CrossOrigin(origins = "*")
@PostMapping(path = "/login",produces = "application/json",consumes = "application/json")
public HashMap<String,String>login(@RequestBody User u){
    return userService.login(u.getEmail(),u.getPassword());


}

@CrossOrigin(origins = "*")
    @PostMapping(path = "/logout",produces = "application/json",consumes = "application/json")
    public String log(@RequestBody User u){
        return userService.logout(u);

}





    }
