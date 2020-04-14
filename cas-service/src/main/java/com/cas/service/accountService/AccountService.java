package com.cas.service.accountService;

import com.cas.pojo.AccountPo;

import javax.servlet.http.HttpServletRequest;

public interface AccountService {

    AccountPo queryAccount(String userId);

    int add();

    void queryAutoId(HttpServletRequest request);

    String pushAutoId();
}
