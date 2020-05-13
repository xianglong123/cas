package com.cas.domain.sms;

import lombok.Data;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 11:12 2020-05-08
 * @version: V1.0
 * @review:
 */
@Data
public class SmsBatchDetail {
    private String keys;
    private String contentVariables;
    private String content;


    public SmsBatchDetail(){}

    public SmsBatchDetail(String content){
        this.content = content;
    }
}
