package com.meng.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mengye
 * @desc
 * @date 2021/2/3 15:20
 */
@RequestMapping("/")
@RestController
public class HomePageController {


    @GetMapping("/")
    public String homePage(){
        return "index";
    }
}
