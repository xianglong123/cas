package com.cas.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AccountPo {

    private String id;
    //用户id
    private String userId;
    //用户余额
    private BigDecimal balance;
    //冻结金额
    private BigDecimal freezeAmount;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

}
