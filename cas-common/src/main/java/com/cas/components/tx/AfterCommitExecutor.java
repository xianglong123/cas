package com.cas.components.tx;

import com.cas.owner.utils.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 10:43 2020-05-13
 * @version: V1.0
 * @review: 事务提交框架
 */
@Slf4j
@Component
public class AfterCommitExecutor extends TransactionSynchronizationAdapter implements Executor {
    private static final ThreadLocal<List<Runnable>> RUNNABLES = new ThreadLocal<>();

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
                ThreadPoolUtil.execute(runnable);
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
