package com.cas.dao.mapper;

import com.cas.pojo.AccountPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountMapper {

    AccountPo queryAccount(@Param("userId") String userId);

    int add();

    List<AccountPo> query();

}
