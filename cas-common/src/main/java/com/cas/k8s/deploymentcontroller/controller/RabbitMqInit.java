package com.cas.k8s.deploymentcontroller.controller;

import com.alibaba.fastjson.JSONObject;
import com.cas.configs.k8s.K8sUtils;
import com.cas.k8s.deploymentcontroller.action.pod.UnifiedPodWatcher;
import com.cas.k8s.deploymentcontroller.crd.mq.RabbitMqCrd;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.apiextensions.CustomResourceDefinition;
import io.fabric8.kubernetes.api.model.apps.StatefulSet;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.handlers.apps.v1.StatefulSetHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 16:47 2020-09-08
 * @version: V1.0 3
 * @review:
 */
@Slf4j
//@Component
public class RabbitMqInit {

    private static final String RABBITMQ_CRD = "rabbitmqcrds.cloud.mq.com";

    @Autowired
    private K8sUtils k8sUtils;

    @Autowired
    private UnifiedPodWatcher unifiedPodWatcher;

    @PostConstruct
    public void init() throws IOException {

        // 创建CRD
        createCrdIfNotExists();

        // 监听pod
        watchPod();

        // 监听RabbitMqCrd的事件
        watchRabbitMqCrd();
    }

    /**
     * 监控crd的变化
     */
    private void watchRabbitMqCrd() throws IOException {

        StatefulSetHandler handler = new StatefulSetHandler();
        k8sUtils.getClient().customResource(RabbitMqCrd.RABBITMQ_CONTEXT).watch("kid-mq", new Watcher<String>() {
            @Override
            public void eventReceived(Action action, String resource) {
                log.info(action + "    ====>   " + resource);
                StatefulSet rabbitMqCrd = JSONObject.parseObject(resource, StatefulSet.class);
//                handler.create()

//                myDeploymentAddedHandler.handle(rabbitMqCrd);
            }

            @Override
            public void onClose(KubernetesClientException cause) {
                log.error("err:  ", cause);
            }
        });



    }


    /**
     * 监控pod的变化
     */
    private void watchPod() {
        k8sUtils.getClient().pods().inNamespace("kid-mq").watch(new Watcher<Pod>() {
            @Override
            public void eventReceived(Action action, Pod pod) {
//                log.info(pod.getMetadata().getOwnerReferences().get(0).getKind() + "  这个pod被监控到");
                // 如果是被rabbitmqCrd管理的pod
                if (pod.getMetadata().getOwnerReferences().stream().anyMatch(ownerReference ->
                        ownerReference.getKind().equals(RabbitMqCrd.CRD_KIND))) {

//                    watch(action);
                    unifiedPodWatcher.eventReceived(action, pod);
                }
            }

            // 先将监控到的改变放缓存队列
//            private void watch(Action action) {
//                switch (action) {
//                    case ADDED: new PodAddedWatcherServer(unifiedPodWatcher); break;
//                    case DELETED: new PodDeletedWatcherServer(unifiedPodWatcher); break;
//                    case MODIFIED: new PodModifiedWatcherServer(unifiedPodWatcher); break;
//                    default:break;
//                }
//            }

            @Override
            public void onClose(KubernetesClientException e) {
                log.error("watching pod caught an exception", e);
            }
        });

    }

    /**
     * 应用启动的时候判断自定义资源类型是否存在，不存在则创建
     * @return
     */
    private void createCrdIfNotExists() {
        log.info("自定义资源初始化开始-[RabbitMqCrd]");
        KubernetesClient client = k8sUtils.getClient();
        // 判断资源是否存在，不存在则创建
        CustomResourceDefinition dummyCRD = client.customResourceDefinitions().withName(RABBITMQ_CRD).get();
        if (dummyCRD != null) {
            System.out.println("Found CRD: " + dummyCRD.getMetadata().getSelfLink());
        } else {
            client.customResourceDefinitions().create(RabbitMqCrd.RABBITMQ_CRD);
            System.out.println("Created CRD " + RabbitMqCrd.RABBITMQ_CRD.getMetadata().getName());
        }
    }


}
