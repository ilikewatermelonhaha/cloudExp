package org.exp.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("provider-2")
public interface UserFeignService {
    @GetMapping("/hi/say")
    String Hi();
}
