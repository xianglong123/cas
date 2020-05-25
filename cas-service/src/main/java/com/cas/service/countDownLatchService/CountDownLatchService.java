package com.cas.service.countDownLatchService;

import java.util.concurrent.ExecutionException;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 20:29 2020-05-21
 * @version: V1.0
 * @review:
 */
public interface CountDownLatchService {

    String subNum(Integer... num);

    String process(Integer... num) throws ExecutionException, InterruptedException;

}
