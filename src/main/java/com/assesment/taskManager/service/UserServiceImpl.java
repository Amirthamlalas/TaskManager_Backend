package com.assesment.taskManager.service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.assesment.taskManager.repository.UserRepository;
import com.assesment.taskManager.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepo;


    @Override
    public HashMap<String,String> login(String email, String password) {

        List<User> result = userRepo.login(email, password);
        String token = generateToken(password,"secretkey123", 3600000);
        HashMap<String,String>map=new HashMap<>();
        if(result.size()==0){
            map.put("status","failed");
        }
        else{
            int id = result.get(0).getId();
            String name = result.get(0).getName();
            LocalDateTime time = result.get(0).getLogout_time();
            map.put("userid",String.valueOf(id));
            map.put("status","success");
            map.put("name",String.valueOf(name));
            map.put("time",String.valueOf(time));
            map.put("token",String.valueOf(token));
        }
        return map;
    }
    @Override
    public String logout(User u){
        u = userRepo.findById(u.getId()).orElseThrow(EntityNotFoundException::new);
        u.setLogout_time(LocalDateTime.now());
        userRepo.save(u);
        return "{\"status\":\"success\"}";
    }

    @Override
    public String generateToken(String subject, String secretKey, long ttlMillis) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        Date expiryDate = new Date(nowMillis + ttlMillis);

        return JWT.create()
                .withSubject(subject)
                .withIssuedAt(now)
                .withExpiresAt(expiryDate)
                .sign(algorithm);
    }
    }


