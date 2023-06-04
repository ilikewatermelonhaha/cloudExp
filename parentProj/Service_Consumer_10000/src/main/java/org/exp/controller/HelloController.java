package org.exp.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
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
    @CircuitBreaker(name="backendA",fallbackMethod="fallback")
    @RateLimiter(name = "myRateLimiter",fallbackMethod = "fallbackRateLimit")
    public String hello() throws InterruptedException {
        System.out.println("enter function!");
        Thread.sleep(100L);
        System.out.println("exit function!");
        return userFeignService.Hello();
    }
    public String fallback(Throwable e){
        e.printStackTrace();
        System.out.println("fallback running!");
        return "service not available";
    }
    public String fallbackRateLimit(Throwable e){
        e.printStackTrace();
        System.out.println("ratelimiter fallback running!");
        return "service not available";
    }
}
