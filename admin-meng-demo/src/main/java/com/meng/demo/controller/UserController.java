package com.meng.demo.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.meng.demo.dto.User;
import com.meng.demo.exception.UserNotExistException;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController{

    @GetMapping
    @JsonView(User.simple.class)
    public List<User> list(){
        ReflectionToStringBuilder.toString("mengye", ToStringStyle.DEFAULT_STYLE);
        List<User> list=new ArrayList();
        list.add(new User(1,"meng",123456,null));
        list.add(new User(2,"geng",123456,null));
        list.add(new User(3,"cheng",123456,null));
        return list;
    }

    //正则匹配数字
    @GetMapping("/{id:\\d+}")
    @JsonView(User.detail.class)
    public User getUserById(@PathVariable("id") Integer id){
        throw new UserNotExistException(id);
       /* System.out.println("进入根据id获取用户信息的方法");
        User user=new User(4,"孟野",123456,null);
        System.out.println("执行完毕获取用户信息的方法");
        return user;*/
    }

    @PostMapping
    public User create(@Validated @RequestBody User user){
        return user;
    }
}
