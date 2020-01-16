package com.demo.springboot.repositories;

import com.demo.springboot.model.User;

import java.util.List;

public interface UserRepository {

    int save(User book);

    int update(User book);

    int deleteById(int id);

    List<User> findAll();

}
