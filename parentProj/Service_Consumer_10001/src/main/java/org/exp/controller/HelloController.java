package org.exp.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
    @GetMapping("/hi")
    @CircuitBreaker(name="backendB",fallbackMethod="fallback")
    @Bulkhead(name = "bulkheadA", fallbackMethod = "fallbackBulkhead", type = Bulkhead.Type.SEMAPHORE)
    public String hi() throws InterruptedException {
        System.out.println("enter function!");
        Thread.sleep(10000L);
        System.out.println("exit function!");
        return userFeignService.Hi();
    }
    public String fallback(Throwable e){
        e.printStackTrace();
        System.out.println("fallback running!");
        return "service not available";
    }
    public String fallbackBulkhead(Throwable e){
        e.printStackTrace();
        System.out.println("bulfhead fallback running!");
        return "service not available";
    }
}

