package com.cas.service.accountService;

import com.cas.dao.mapper.AccountMapper;
import com.cas.pojo.AccountPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceClient implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public AccountPo queryAccount(String userId) {
        return accountMapper.queryAccount(userId);
    }
}
