package com.example.hystrix.service;

import com.common.Result;

/**
 * @author YanCh
 * @version v1.0
 * Create by 2022-03-25 18:17
 **/
public interface UserService {

    /**
     * 获取用户
     * @param id
     * @return
     */
    Result getUser(Long id);


    Result getUserCommand(Long id);
}
