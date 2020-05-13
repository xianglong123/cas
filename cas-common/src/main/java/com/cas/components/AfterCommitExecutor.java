package com.cas.components;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 10:43 2020-05-13
 * @version: V1.0
 * @review:
 */
@Slf4j
@Component
public class AfterCommitExecutor extends TransactionSynchronizationAdapter implements Executor {
    private static final ThreadLocal<List<Runnable>> RUNNABLES = new ThreadLocal<List<Runnable>>();
    private ExecutorService threadPool = Executors.newFixedThreadPool(5);


    @Override
    public void execute(Runnable command) {
      log.info("AfterCommitExecutor被执行");
        if (!TransactionSynchronizationManager.isSynchronizationActive()) {
            log.info("Transaction synchronization is NOT ACTIVE. Executing right now runnable {}", command);
            command.run();
            return;
        }
        List<Runnable> threadRunnables = RUNNABLES.get();
        if (threadRunnables == null) {
            threadRunnables = new ArrayList<>();
            RUNNABLES.set(threadRunnables);
            TransactionSynchronizationManager.registerSynchronization(this);
        }
        threadRunnables.add(command);
    }


    @Override
    public void afterCommit() {
        List<Runnable> threadRunnables = RUNNABLES.get();
        log.info("Transaction successfully committed, executing {} runnables", threadRunnables.size());
        for (int i = 0; i < threadRunnables.size(); i++) {
            Runnable runnable = threadRunnables.get(i);
            log.info("Executing runnable {}", runnable);
            try {
                threadPool.execute(runnable);
            } catch (RuntimeException e) {
                log.error("Failed to execute runnable " + runnable, e);
            }
        }
    }

    @Override
    public void afterCompletion(int status) {
        log.info("Transaction completed with status {}", status == STATUS_COMMITTED ? "COMMITTED" : "ROLLED_BACK");
        RUNNABLES.remove();
    }

}
