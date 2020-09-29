package com.cas.configs.k8s;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 11:21 2020-08-28
 * @version: V1.0
 * @review:
 */
@Configuration
@EnableConfigurationProperties(K8sConfig.K8sProperties.class)
public class K8sConfig {

    @Autowired
    private K8sProperties k8sProperties;


    @Data
    @ConfigurationProperties("k8s")
    public static class K8sProperties{

        private String url;

        @Value("client-crt")
        private Resource clientCrt;

        @Value("client-key")
        private Resource clientKey;

        @Value("ca-crt")
        private Resource caCrt;
    }

    @Bean
    public K8sUtils k8sUtils() {
        if (k8sProperties == null) {
            return null;
        }
        final String url = k8sProperties.getUrl();
        if(StringUtils.isEmpty(url)){
            return null;
        }
        return new K8sUtils(k8sProperties);
    }


}
