package com.cas.domain.sms;

import lombok.Data;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 11:10 2020-05-08
 * @version: V1.0
 * @review:
 */
@Data
public class SmsBatch {
    private String Keys;
    private String ContentVariables;
    private String Content;
}
