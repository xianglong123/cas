package com.cas.k8s.deploymentcontroller.crd.cluster;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.KubernetesResource;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 16:48 2020-09-08
 * @version: V1.0
 * @review:
 */
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
public class RabbitMqClusterStatus implements KubernetesResource {
}
