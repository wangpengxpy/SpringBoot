package com.demo.springboot.controllers;

import com.demo.springboot.model.User;
import com.demo.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import util.PagedResult;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @Autowired
    @Qualifier("namedParameterJdbcUserRepository")
    private UserRepository jdbcUserRepository;

    @ModelAttribute("user")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User user() {
        User user = new User();
        user.setGender("M");
        user.setFavorites(new String[]{"乒乓球", "羽毛球", "台球"});
        return user;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String user(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user";
        } else {
            jdbcUserRepository.save(user);
            return "users";
        }
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String Users() {
        return "users";
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    @ResponseBody
    public PagedResult<User> getUsers(@RequestParam(defaultValue = "0") int offset,
                                      @RequestParam(defaultValue = "10") int limit) {

        List<User> users = jdbcUserRepository.findAll();

        int total = users.size();

        users = users.stream()
                .skip(offset)
                .limit(limit)
                .collect(Collectors.toCollection(ArrayList::new));

        return new PagedResult(total, users);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public boolean delete(@PathVariable(value = "id") int id) {
        int result = jdbcUserRepository.deleteById(id);
        return result > 0;
    }

    @ModelAttribute("favorites")
    public Object[] getfavoriteList() {
        List<String> favorites = new ArrayList<>();
        favorites.add("足球");
        favorites.add("乒乓球");
        favorites.add("羽毛球");
        favorites.add("台球");
        return favorites.toArray();
    }

    @ModelAttribute("countryList")
    public Map<String, String> getCountryList() {
        Map<String, String> countryList = new HashMap<>();
        countryList.put("CHI", "中国");
        countryList.put("CH", "英国");
        countryList.put("SG", "新加坡");
        return countryList;
    }
}