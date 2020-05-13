package com.cas.service.accountService;

import com.cas.dao.mapper.AccountMapper;
import com.cas.pojo.AccountPo;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@Service
public class AccountServiceClient implements AccountService {

    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    private static final Queue<AsyncContext> CONNECTIONS = new ConcurrentLinkedQueue<>();

    private static Map<String, Integer> autoMap = new HashMap<String, Integer>(){{
        put("num", 1);
    }};


    static {
        THREAD_LOCAL.set("123456789");
    }

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public AccountPo queryAccount(String userId) {
        return accountMapper.queryAccount(userId);
    }

    /**
     * 一个语句本身就是原子性的，要什么事务，🐷
     *
     * @return
     */
    @Override
    @CachePut(value = "redisCache", key = "'redis_user_1'")
//    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Transactional
    public int add() {
        redisTemplate.opsForValue().set("xianlgong", "");
        if(redisTemplate.opsForValue().setIfAbsent("a", "")) {
            log.info("ok");
        }
        return accountMapper.add();
    }

    @Override
    public void queryAutoId(HttpServletRequest request) {
        AsyncContext asyncContext = request.startAsync();
        asyncContext.setTimeout(0);
        CONNECTIONS.offer(asyncContext);
    }

    @Override
    public String pushAutoId() {
        log.info("反向ajax被调用...");
        for(AsyncContext context : CONNECTIONS) {
            HttpServletResponse response = (HttpServletResponse) context.getResponse();
            try {
                PrintWriter out = response.getWriter();
                out.print(autoMap.get("num"));
                out.flush();
                out.close();
                context.complete();
                CONNECTIONS.remove(context);
                autoMap.put("num",autoMap.get("num")+1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
