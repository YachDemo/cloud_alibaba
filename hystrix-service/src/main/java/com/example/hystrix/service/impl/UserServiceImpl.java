package com.example.hystrix.service.impl;

import com.common.Result;
import com.common.entity.User;
import com.example.hystrix.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
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
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

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

    @HystrixCommand(
            fallbackMethod = "getDefaultUser",
            commandKey = "getUserCommand",
            groupKey = "getUserGroup",
            threadPoolKey = "getUserThreadPool"
    )
    @Override
    public Result getUserCommand(Long id) {
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", Result.class, id);
    }

    @HystrixCommand(
            fallbackMethod = "getDefaultUser2",
            ignoreExceptions = {NullPointerException.class}
    )
    @Override
    public Result getUserException(Long id) {
        if (id == 1) {
            throw new NullPointerException();
        } else if (id == 2) {
            throw new IllegalArgumentException();
        }
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", Result.class, id);

    }

    public Result getDefaultUser2(@PathVariable Long id, Throwable e) {
        log.error("getDefaultUser2 id:{},throwable class:{}", id, e.getClass());
        User user = new User(-2L, "12312", "34123");
        return Result.success(user);
    }


    @Override
    @HystrixCommand(
            fallbackMethod = "getDefaultUser",
            commandKey = "getUserCache")
    @CacheResult(cacheKeyMethod = "getCacheKey")
    public Result getUserCache(Long id) {
        log.info("getUserCache id:{}", id);
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", Result.class, id);
    }

    public String getCacheKey(Long id) {
        return id.toString();
    }

}
