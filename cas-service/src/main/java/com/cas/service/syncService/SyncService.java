package com.cas.service.syncService;

import java.util.concurrent.ExecutionException;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 20:29 2020-05-21
 * @version: V1.0
 * @review:
 */
public interface SyncService {

    String subNum(Integer... num);

    String process(Integer... num) throws ExecutionException, InterruptedException;

}
