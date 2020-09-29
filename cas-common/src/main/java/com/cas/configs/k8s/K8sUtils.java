package com.cas.configs.k8s;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Bean;

import java.util.Base64;

@Slf4j
public class K8sUtils {

    private static final String HTTPS_PREFIX = "https";
    private KubernetesClient client;
    private Config config;
    public K8sUtils(K8sConfig.K8sProperties k8sProperties){
        try {
            final String k8sUrl = k8sProperties.getUrl();
            log.info("k8sUrl : {}", k8sUrl);
            if(k8sUrl.startsWith(HTTPS_PREFIX)) {

                final String caCrtData = IOUtils.toString(k8sProperties.getCaCrt().getInputStream());
                final String clientKeyData = IOUtils.toString(k8sProperties.getClientKey().getInputStream());
                // 注意：此处必须先用 Base64 对证书内容加密，否则会提示 input null
                final String clientCrtData = Base64.getEncoder().encodeToString(IOUtils.toByteArray(k8sProperties.getClientCrt().getInputStream()));
                log.info("caCrtFile data: {} ", caCrtData);
                log.info("clientKeyFile data: {} ", clientKeyData);
                log.info("clientCrtFile data: {} ", clientCrtData);

                config = new ConfigBuilder().withMasterUrl(k8sUrl)
                        .withTrustCerts(true)
                        .withCaCertData(caCrtData)
                        .withClientCertData(clientCrtData)
                        .withClientKeyData(clientKeyData)
                        // 需将 Namespace 初始化为 null
                        .withNamespace(null)
                        .build();
            }else {
                config = new ConfigBuilder().withMasterUrl(k8sUrl).build();
            }
            client = new DefaultKubernetesClient(config);
        }catch (Exception e){
            client = null;
            log.error("初始化 K8sUtils 失败！", e);
        }
    }

    @Bean
    public KubernetesClient getClient() {
        return client;
    }
    public Config geConfigtConfig(){
        return config;
    }

}
