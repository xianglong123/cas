package com.cas.dao.mapper;

import com.cas.pojo.AccountPo;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper {

    AccountPo queryAccount(@Param("userId") String userId);

}
