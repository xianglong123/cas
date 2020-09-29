package com.cas.k8s.deploymentcontroller.crd.cluster;

import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.CustomResource;
import lombok.Data;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 11:04 2020-09-22
 * @version: V1.0
 * @review:
 */
@Data
public class RabbitMqCluster extends CustomResource implements Namespaced{

//    @JsonProperty("spec")
    private RabbitMqClusterSpec spec;

//    @JsonProperty("status")
    private RabbitMqClusterStatus status;



    @Override
    public ObjectMeta getMetadata() {
        return super.getMetadata();
    }

    @Override
    public String getApiVersion() {
        return "rabbitmq.com/v1beta1";
    }

    @Override
    public String toString() {
        return "RabbitMqCluster{" +
                "spec=" + spec +
                ", status=" + status +
                '}';
    }
}
