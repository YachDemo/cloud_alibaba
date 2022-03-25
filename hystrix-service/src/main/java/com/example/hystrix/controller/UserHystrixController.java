package com.example.hystrix.controller;

import com.common.Result;
import com.example.hystrix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YanCh
 * @version v1.0
 * Create by 2022-03-25 17:26
 **/
@RestController
@RequestMapping("user")
public class UserHystrixController {

    @Autowired
    private UserService userService;


    @GetMapping("/testFallback/{id}")
    public Result testFallback(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/testCommand/{id}")
    public Result testCommand(@PathVariable Long id) {
        return userService.getUserCommand(id);
    }
}
