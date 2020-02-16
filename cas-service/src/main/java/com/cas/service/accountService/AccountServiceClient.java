package com.cas.service.accountService;

import com.cas.dao.mapper.AccountMapper;
import com.cas.pojo.AccountPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceClient implements AccountService {

    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    static {
        THREAD_LOCAL.set("123456789");
    }

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public AccountPo queryAccount(String userId) {
        return accountMapper.queryAccount(userId);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int add() {
        return accountMapper.add();
    }
}
