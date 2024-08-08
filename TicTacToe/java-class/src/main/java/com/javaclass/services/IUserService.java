package com.javaclass.services;

import com.javaclass.entities.User;

import java.util.Optional;

public interface IUserService {

     void saveUser(String name);
    void winner(String name);
    void loser(String name);
    void draw(String name);
}
