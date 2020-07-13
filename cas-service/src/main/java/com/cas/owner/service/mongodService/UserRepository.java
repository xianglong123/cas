package com.cas.owner.service.mongodService;

import com.cas.domain.User;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 14:56 2020-07-03
 * @version: V1.0
 * @review:
 */
public interface UserRepository{

    void saveUser(User user);

    User findUserByUserName(String userName);

    long updateUser(User user);

    void deleteUserById(String username);

}
