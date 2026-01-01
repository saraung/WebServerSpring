package com.saraung.WebApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Controller
public class HomeController {


    @RequestMapping("/")
//    @ResponseBody
    public String greet(){
        return "welcome to saraung";
    }
}
