package com.assesment.taskManager.service;

import com.assesment.taskManager.model.User;

import java.util.HashMap;
import java.util.List;

public interface UserService {

    public HashMap<String,String> login(String email, String password);

    public String logout(User u);
    public String generateToken(String subject, String secretKey, long ttlMillis);
}
