package com.cas.k8s.deploymentcontroller.crd.cluster;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 11:38 2020-09-22
 * @version: V1.0
 * @review:
 */
@JsonDeserialize(
        using = JsonDeserializer.None.class
)

@Data
public class RabbitmqClusterConfigurationSpec {

    private String[] additionalPlugins;

    private String additionalConfig;

//    private String advancedConfig;

//    private String envConfig;


}
