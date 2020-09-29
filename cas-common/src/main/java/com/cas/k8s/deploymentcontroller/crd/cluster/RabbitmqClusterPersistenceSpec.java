package com.cas.k8s.deploymentcontroller.crd.cluster;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 11:22 2020-09-22
 * @version: V1.0
 * @review:
 */
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@Data
public class RabbitmqClusterPersistenceSpec {

    private String storageClassName;

    private io.fabric8.kubernetes.api.model.Quantity storage;

}
