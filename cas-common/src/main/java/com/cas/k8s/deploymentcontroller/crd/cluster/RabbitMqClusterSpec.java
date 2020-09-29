package com.cas.k8s.deploymentcontroller.crd.cluster;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import lombok.Data;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 16:48 2020-09-08
 * @version: V1.0
 * @review:
 */
@JsonDeserialize(
        using = JsonDeserializer.None.class
)

@Data
public class RabbitMqClusterSpec implements KubernetesResource {

    private Integer replicas;

    private String image;

//    @JsonProperty("imagePullSecret")
//    private String imagePullSecret;
//
//    @JsonProperty("service")
//    private RabbitMqClusterServiceSpec service;

    private RabbitmqClusterPersistenceSpec persistence;

    private RabbitmqClusterConfigurationSpec rabbitmq;

}
