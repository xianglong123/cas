package com.cas.k8s.deploymentcontroller.crd.mq;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 16:48 2020-09-08
 * @version: V1.0
 * @review:
 */

@JsonDeserialize(using = com.fasterxml.jackson.databind.JsonDeserializer.None.class)

@ToString
@EqualsAndHashCode
@Data
public class RabbitMqCrdSpec implements KubernetesResource {


    @JsonProperty("serviceName")
    private String serviceName;

    @JsonProperty("replicas")
    private Integer replicas;

    @JsonProperty("selector")
    private io.fabric8.kubernetes.api.model.LabelSelector selector;


    @JsonProperty("template")
    private io.fabric8.kubernetes.api.model.PodTemplateSpec template;

}
