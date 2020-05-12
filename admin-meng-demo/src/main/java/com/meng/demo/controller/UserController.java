package com.meng.demo.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.meng.demo.dto.User;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/user")
    @JsonView(User.simple.class)
    public List<User> list(){
        ReflectionToStringBuilder.toString("mengye", ToStringStyle.DEFAULT_STYLE);
        List<User> list=new ArrayList();
        list.add(new User("meng",123456));
        list.add(new User("geng",123456));
        list.add(new User("cheng",123456));
        return list;
    }

    //正则匹配数字
    @GetMapping("/user/{id:\\d+}")
    @JsonView(User.detail.class)
    public User getUserById(@PathVariable("id") Integer id){
        User user=new User("孟野",123456);
        return user;
    }
}
