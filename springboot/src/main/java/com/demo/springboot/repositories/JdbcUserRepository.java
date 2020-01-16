package com.demo.springboot.repositories;

import com.demo.springboot.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcUserRepository implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(User user) {
        return jdbcTemplate.update(
                "insert into users (userName, password,firstName,lastName,gender,email,country,favorites)" +
                        " values(?,?,?,?,?,?,?,?)",
                user.getUserName(), user.getPassword(), user.getFirstName(),
                user.getLastName(), user.getGender(), user.getEmail(),
                user.getCountry(), user.getFavoritesJson());
    }

    @Override
    public int update(User user) {
        return jdbcTemplate.update(
                "update users " +
                        "set userName = ?,password=?,firstName=?," +
                        "lastName=?,gender=?,email=?,country=?,favorites=?, " +
                        "where id = ?",
                user.getFirstName(), user.getPassword(), user.getFirstName(),
                user.getLastName(), user.getGender(), user.getEmail(),
                user.getCountry(), user.getFavoritesJson(), user.getUserId());
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("delete from  users where userId = ?", id);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(
                "select * from users",
                (rs, rowNum) ->
                {
                    try {
                        return new User(
                                rs.getInt("userId"),
                                rs.getString("firstName"),
                                rs.getString("lastName"),
                                rs.getString("gender"),
                                rs.getString("email"),
                                rs.getString("userName"),
                                rs.getString("password"),
                                rs.getString("country"),
                                rs.getString("favorites")
                        );
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        return  null;
                    }
                });
    }
}
