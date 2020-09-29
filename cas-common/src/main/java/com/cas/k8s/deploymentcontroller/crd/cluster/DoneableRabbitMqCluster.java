package com.cas.k8s.deploymentcontroller.crd.cluster;

import io.fabric8.kubernetes.api.builder.Function;
import io.fabric8.kubernetes.client.CustomResourceDoneable;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 11:03 2020-09-22
 * @version: V1.0
 * @review:
 */
public class DoneableRabbitMqCluster extends CustomResourceDoneable<RabbitMqCluster> {


    public DoneableRabbitMqCluster(RabbitMqCluster resource, Function<RabbitMqCluster, RabbitMqCluster> function) {
        super(resource, function);
    }
}
