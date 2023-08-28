package com.assesment.taskManager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String email;
    private String password;

    private LocalDateTime logout_time;

    public User() {
    }

    public User(int id, String name, String email, String password, LocalDateTime logout_time) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.logout_time = logout_time;

    }



    public LocalDateTime getLogout_time() {
        return logout_time;
    }

    public void setLogout_time(LocalDateTime logout_time) {
        this.logout_time = logout_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
