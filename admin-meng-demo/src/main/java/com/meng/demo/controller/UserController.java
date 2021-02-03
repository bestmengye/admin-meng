package com.meng.demo.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.meng.demo.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author mengye
 * @Desc 用户控制器
 * @date 2020/5/18 15:25
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @PostMapping("/regist")
    public void regist(User user, HttpServletRequest request) {
        String userId = user.getUsername();

        //注册用户
        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
    }

    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
        return user;
    }

    @GetMapping
    @JsonView(User.Simple.class)
    public List<User> list() {
        ReflectionToStringBuilder.toString("mengye", ToStringStyle.DEFAULT_STYLE);
        List<User> list = new ArrayList();
        list.add(new User(1, "meng", 123456, null));
        list.add(new User(2, "geng", 123456, null));
        list.add(new User(3, "cheng", 123456, null));
        return list;
    }

    /**
     * 查询用户id信息
     *
     * @param id 只可以匹配数字
     * @return
     */
    @GetMapping("/{id:\\d+}")
    @JsonView(User.Detail.class)
    public Callable<User> getUserById(@PathVariable("id") Integer id) throws InterruptedException {
//        throw new UserNotExistException(id);

        log.info("-------------------------------------进入根据id获取用户信息的方法");
        Callable<User> userCallable = new Callable<User>() {

            @Override
            public User call() throws Exception {
                log.info("我是子线程 start --------------");
                User user = new User();
                user.setAge(200);
                user.setUsername("我是子的线程");
                log.info("我是子线程 end --------------");
                return user;
            }
        };
        log.info("-------------------------------------执行完毕获取用户信息的方法");
        return userCallable;
    }

    @PostMapping
    public User create(@Validated @RequestBody User user) {
        return user;
    }

}
