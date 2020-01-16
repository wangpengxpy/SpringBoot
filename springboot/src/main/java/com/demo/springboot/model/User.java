package com.demo.springboot.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class User {

    private ObjectMapper objectMapper = new ObjectMapper();

    public User() {
    }

    public User(int userId,
                String firstName,
                String lastName,
                String gender,
                String email,
                String userName,
                String password,
                String country,
                String favoritesJson) throws JsonProcessingException {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.country = country;

        this.favorites = objectMapper.readValue(favoritesJson, new TypeReference<String[]>() {
        });
    }

    private int userId;
    @NotNull(message = "名字必填")
    private String firstName;
    @NotNull(message = "姓氏必填")
    private String lastName;
    @NotNull(message = "性别必填")
    private String gender;
    @NotNull(message = "邮箱必填")
    @Email(message = "请输入有效的邮箱")
    private String email;
    @NotNull(message = "用户名必填")
    private String userName;
    @NotNull(message = "密码必填")
    private String password;
    private String country;

    public int getUserId() {
        return userId;
    }

    public String getFavoritesJson() {
        String favoritesJson = null;
        try {
            favoritesJson = objectMapper.writeValueAsString(this.favorites);
        } catch (JsonProcessingException ex) {
        }
        return favoritesJson;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private String[] favorites;

    public String[] getFavorites() {
        return favorites;
    }

    public void setFavorites(String[] favorites) {
        this.favorites = favorites;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
