package org.exp.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
@FeignClient(name="provider")
public interface UserFeignService {
    @GetMapping("/hello/say")
    String Hello();

}
