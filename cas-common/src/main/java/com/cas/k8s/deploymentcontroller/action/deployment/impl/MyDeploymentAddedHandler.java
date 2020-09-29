package com.cas.k8s.deploymentcontroller.action.deployment.impl;

import com.cas.configs.k8s.K8sUtils;
import com.cas.k8s.deploymentcontroller.action.pod.UnifiedPodWatcher;
import com.cas.k8s.deploymentcontroller.crd.mq.RabbitMqCrd;
import io.fabric8.kubernetes.api.model.Pod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.locks.LockSupport;
import java.util.stream.Collectors;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 16:41 2020-09-08
 * @version: V1.0
 * @review:
 */
@Slf4j
//@Component
public class MyDeploymentAddedHandler {

    @Autowired
    private K8sUtils k8sUtils;

    public void handle(RabbitMqCrd rabbitMqCrd) {
        log.info("{} added", rabbitMqCrd.getMetadata().getName());
        // TODO 当第一次启动项目时，现存的MyDeployment会回调一次Added事件，这里会导致重复创建pod【可通过status解决】,目前解法是去查一下现存的pod[不可靠]
        // 有可能pod的状态还没来得及置为not ready

        int existedReadyPodNumber = k8sUtils.getClient().pods().inNamespace(rabbitMqCrd.getMetadata().getNamespace())
                .withLabelSelector(rabbitMqCrd.getSpec().getSelector()).list().getItems()
                .stream().filter(UnifiedPodWatcher::isPodReady).collect(Collectors.toList()).size();

        Integer replicas = rabbitMqCrd.getSpec().getReplicas();

        // todo 在执行创建pod之前检查pod是否准备完全
        for (int i = 0; i < replicas - existedReadyPodNumber; i ++) {

            Pod pod = rabbitMqCrd.createPod();
            log.info("Thread {}:creating pod[{}]: {} , {}", Thread.currentThread().getId(), i, pod.getMetadata().getName(), pod);

            k8sUtils.getClient().pods().create(pod);


            if (UnifiedPodWatcher.isPodReady(pod)) {
                LockSupport.unpark(Thread.currentThread());
                log.info("线程被挂起");
            }
        }

    }
}
