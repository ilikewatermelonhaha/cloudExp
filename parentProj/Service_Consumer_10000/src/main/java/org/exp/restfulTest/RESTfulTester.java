package org.exp.restfulTest;
import org.exp.entity.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class RESTfulTester {
    private static final String BASE_URL = "http://localhost:11000";

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        // GET 请求, 获取id为123的用户
        ResponseEntity<String> getResponse = restTemplate.getForEntity(BASE_URL + "/users/{id}", String.class, 123);
        System.out.println("GET Response: " + getResponse.getBody());
        // POST 请求, 新增一个用户
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        User user = new User(1234L, "John","abc@123.com");
        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
        ResponseEntity<String> postResponse = restTemplate.postForEntity(BASE_URL + "/users", requestEntity, String.class);
        System.out.println("POST Response: " + postResponse.getBody());
        // PUT 请求, 将刚刚新增的用户name改为Linda
        String putUrl = BASE_URL + "/users/{id}";
        // 请求体参数
        User user1 = new User(1234L, "Linda","abc@123.com");
        HttpEntity<User> putRequestEntity = new HttpEntity<>(user1, headers);
        restTemplate.put(putUrl, putRequestEntity, 1234);
        System.out.println("PUT Request sent.");
        // DELETE 请求, 删除1234用户
        String deleteUrl = BASE_URL + "/users/{id}";
        restTemplate.delete(deleteUrl, 1234);
        System.out.println("DELETE Request sent.");
    }
}
