package com.cas.k8s.deploymentcontroller.crd.mq;

import io.fabric8.kubernetes.api.builder.Function;
import io.fabric8.kubernetes.client.CustomResourceDoneable;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 16:49 2020-09-08
 * @version: V1.0
 * @review:
 */
public class DoneableRabbitMqCrd extends CustomResourceDoneable<RabbitMqCrd> {

    public DoneableRabbitMqCrd(RabbitMqCrd resource, Function<RabbitMqCrd, RabbitMqCrd> function) {
        super(resource, function);
    }
}
