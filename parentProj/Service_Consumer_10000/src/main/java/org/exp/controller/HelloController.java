package org.exp.controller;

import org.exp.loadbalancer.CustomLoadBalanceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.exp.feign.UserFeignService;

@RestController
@RequestMapping("/call")
@LoadBalancerClient(name="provider",configuration = CustomLoadBalanceConfiguration.class)
public class HelloController {

    @Autowired
    private UserFeignService userFeignService;

    @GetMapping("/hello")
    public String hello(){
        return userFeignService.Hello();
    }
}
