package com.cas.owner.service.mongodService;

import com.cas.domain.User;
import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 14:55 2020-07-03
 * @version: V1.0
 * @review:
 */
@Component
public class UserRepositoryImpl implements UserRepository{

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public void saveUser(User user) {
        mongoTemplate.save(user);
    }

    @Override
    public User findUserByUserName(String userName) {
        Query query = new Query(Criteria.where("username").is(userName));
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public long updateUser(User user) {
        Query query = new Query(Criteria.where("username").is(user.getUsername()));
        Update update = new Update().set("username", user.getUsername()).set("password", user.getPassword());
        // 更新查询返回结果集的第一条
        WriteResult result = mongoTemplate.updateFirst(query, update, User.class);
        return result != null ? result.getN() : 0;
    }

    @Override
    public void deleteUserById(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        mongoTemplate.remove(query, User.class);
    }
}
