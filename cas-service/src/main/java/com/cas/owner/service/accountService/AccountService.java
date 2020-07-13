package com.cas.owner.service.accountService;

import com.cas.pojo.AccountPo;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public interface AccountService {

    AccountPo queryAccount(String userId);

    int add();

    void queryAutoId(HttpServletRequest request);

    String pushAutoId();

    int insertAccount() throws SQLException;

    int insertAccount2() throws SQLException;
}
