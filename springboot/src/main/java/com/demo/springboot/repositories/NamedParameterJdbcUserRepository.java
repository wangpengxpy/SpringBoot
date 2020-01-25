package com.demo.springboot.repositories;

import com.demo.springboot.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NamedParameterJdbcUserRepository implements UserRepository {

    @Autowired
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int save(User user) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("userName",user.getUserName());
        mapSqlParameterSource.addValue("password",user.getPassword());
        mapSqlParameterSource.addValue("firstName",user.getFirstName());
        mapSqlParameterSource.addValue("lastName",user.getLastName());
        mapSqlParameterSource.addValue("gender",user.getGender());
        mapSqlParameterSource.addValue("email",user.getEmail());
        mapSqlParameterSource.addValue("country",user.getCountry());
        mapSqlParameterSource.addValue("favorites",user.getFavoritesJson());

        return namedParameterJdbcTemplate.update(
                "insert into users (userName, password,firstName,lastName,gender,email,country,favorites)" +
                        " values(:userName,:password,:firstName,:lastName,:gender,:email,:country,:favorites)",
             mapSqlParameterSource);
    }

    @Override
    public int update(User user) {
        return 0;
    }

    @Override
    public int deleteById(int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("userId", id);
        return namedParameterJdbcTemplate.update("delete from  users where userId = :userId", mapSqlParameterSource);
    }

    @Override
    public List<User> findAll() {
        return namedParameterJdbcTemplate.query(
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
                        return null;
                    }
                });
    }
}
