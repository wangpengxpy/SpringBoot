package com.demo.springboot.repositories;

import com.demo.springboot.model.User;

import java.util.List;

public interface UserRepository {

    int save(User user);

    int update(User user);

    int deleteById(int id);

    List<User> findAll();

}
