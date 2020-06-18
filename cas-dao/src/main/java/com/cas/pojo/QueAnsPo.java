package com.cas.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 17:47 2020-02-24
 * @version: V1.0
 * @review:
 */
@Data
public class QueAnsPo {

    // 主键
    private String uuid;
    // 问题描述
    private String question;
    // 答案
    private String answer;
    // 关键词
    private String keywords;
    // 问题地址
    private String questionUrl;
    // 操作人
    private String operation;
    // 创建时间
    private Date createTime;
    // 更新时间
    private Date uptTime;

}
