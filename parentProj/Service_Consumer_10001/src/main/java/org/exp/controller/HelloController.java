package org.exp.controller;

import org.exp.feign.UserFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/call")
public class HelloController {

    @Autowired
    private UserFeignService userFeignService;

    @GetMapping("/hello")
    public String hello(){
        return userFeignService.Hello();
    }
}

