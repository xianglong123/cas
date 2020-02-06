package com.cas.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 15:17 2020-01-20
 * @version: V1.0
 * @review:
 */
@Component
@Data
public class Merch {

    @Value("tt")
    private String username;

    @Value("000000")
    private String alie;

    private Merch() {}

    public Merch(String username, String alie) {
        this.username = username;
        this.alie = alie;
    }
}
