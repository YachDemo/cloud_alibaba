package com.example.hystrix.service.impl;

import com.common.Result;
import com.common.entity.User;
import com.example.hystrix.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

/**
 * @author YanCh
 * @version v1.0
 * Create by 2022-03-25 18:17
 **/
@Service
public class UserServiceImpl implements UserService {
    @Value("${serverUrl.userService}")
    private String userServiceUrl;
    @Autowired
    private RestTemplate restTemplate;


    @Override
    @HystrixCommand(fallbackMethod = "getDefaultUser")
    public Result getUser(Long id) {
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", Result.class, id);
    }

    public Result getDefaultUser(@PathVariable Long id) {
        User user = new User(-1L, "12312", "34123");
        return Result.success(user);
    }

    @Override
    public Result getUserCommand(Long id) {

        return null;
    }
}
