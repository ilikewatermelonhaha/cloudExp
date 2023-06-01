package org.exp.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.exp.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/users")
public class RestfulProvider {

    private List<User> users=new ArrayList<>();
    RestfulProvider(){
        //初始化用户列表
        users.add(new User(123L,"testUser","test@mail.com"));
    }
    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id) {
        // 根据用户ID获取用户信息的逻辑
        User result=users.stream().filter(u->u.getId()==id).findFirst().get();
        System.out.println("触发GET");
        System.out.println(users);
        return result.toString();
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody String userString) throws JsonProcessingException {
        // 创建新用户的逻辑
        // user 参数包含了通过请求体传递的用户信息
        ObjectMapper objectMapper = new ObjectMapper();
        User user=objectMapper.readValue(userString, User.class);
        users.add(user);
        System.out.println("触发POST");
        System.out.println(users);
        return ResponseEntity.ok("User created: " + user.toString());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody String userString) throws JsonProcessingException {
        // 更新用户信息的逻辑
        // id 参数表示要更新的用户ID
        System.out.println("触发PUT");
        ObjectMapper objectMapper = new ObjectMapper();
        User user=objectMapper.readValue(userString, User.class);
        users=users.stream().map(u -> u.getId().equals(id) ? user : u).collect(Collectors.toList());
        System.out.println(users);
        return ResponseEntity.ok("User updated: " + user.toString());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        // 删除用户的逻辑
        System.out.println("触发DELETE");
        users=users.stream().filter(u->!u.getId().equals(id)).collect(Collectors.toList());
        System.out.println(users);
        return ResponseEntity.ok("User deleted: " + id);
    }
}
