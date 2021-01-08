package com.cas.controller.tsController;

import com.cas.configs.k8s.K8sUtils;
import com.cas.k8s.deploymentcontroller.crd.cluster.DoneableRabbitMqCluster;
import com.cas.k8s.deploymentcontroller.crd.cluster.RabbitMqCluster;
import com.cas.k8s.deploymentcontroller.crd.cluster.RabbitMqClusterList;
import com.cas.k8s.deploymentcontroller.crd.cluster.RabbitMqClusterSpec;
import com.cas.k8s.deploymentcontroller.crd.cluster.RabbitmqClusterConfigurationSpec;
import com.cas.k8s.deploymentcontroller.crd.cluster.RabbitmqClusterPersistenceSpec;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.NamespaceBuilder;
import io.fabric8.kubernetes.api.model.NamespaceList;
import io.fabric8.kubernetes.api.model.ObjectMetaBuilder;
import io.fabric8.kubernetes.api.model.PersistentVolume;
import io.fabric8.kubernetes.api.model.PersistentVolumeBuilder;
import io.fabric8.kubernetes.api.model.Quantity;
import io.fabric8.kubernetes.api.model.ServiceList;
import io.fabric8.kubernetes.api.model.apiextensions.CustomResourceDefinition;
import io.fabric8.kubernetes.api.model.apiextensions.CustomResourceDefinitionBuilder;
import io.fabric8.kubernetes.api.model.apiextensions.CustomResourceDefinitionVersionBuilder;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.fabric8.kubernetes.client.dsl.base.CustomResourceDefinitionContext;
import io.fabric8.kubernetes.internal.KubernetesDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 09:27 2020-08-26
 * @version: V1.0
 * @review:
 */

@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@Slf4j
@RestController
@RequestMapping("/k8s")
public class K8sController {

    private static Config config = new ConfigBuilder().withMasterUrl("https://kubernetes.docker.internal:6443").build();


    @Autowired
    private K8sUtils k8sUtils;

    private CustomResourceDefinition rabbitmqCluster;
    private CustomResourceDefinitionContext crdContext;

    static {
        KubernetesDeserializer.registerCustomKind("rabbitmq.com/v1beta1", "RabbitmqCluster", RabbitMqCluster.class);
    }

    /**
     * 添加mq集群
     * @return
     */
    @RequestMapping("/addMqCluster")
    public String addMqCluster() {
        KubernetesClient client = k8sUtils.getClient();
        rabbitmqCluster = client
                .customResourceDefinitions()
                .load(getClass().getResourceAsStream("/crd/rabbitmq_com_rabbitmqcluster.yaml"))
                .get();
        crdContext = CustomResourceDefinitionContext.fromCrd(rabbitmqCluster);
        MixedOperation<RabbitMqCluster, RabbitMqClusterList, DoneableRabbitMqCluster, Resource<RabbitMqCluster, DoneableRabbitMqCluster>> mqclient = client
                .customResources(crdContext, RabbitMqCluster.class, RabbitMqClusterList.class, DoneableRabbitMqCluster.class);
        RabbitMqCluster cluster = createCluster();
        mqclient.inNamespace("default").create(cluster);
        return "ok";
    }


    public RabbitMqCluster createCluster() {
        RabbitMqCluster rabbitMqCluster = new RabbitMqCluster();
        rabbitMqCluster.setApiVersion("rabbitmq.com/v1beta1");
        rabbitMqCluster.setKind("RabbitmqCluster");
        rabbitMqCluster.setMetadata(new ObjectMetaBuilder().withName("plugins").build());

        RabbitMqClusterSpec spec = new RabbitMqClusterSpec();
        spec.setReplicas(2);
        spec.setImage("hub.atguigu.com/library/rabbitmq:v2");

        String[] strings = new String[]{"rabbitmq_federation", "rabbitmq_federation_management"};
        RabbitmqClusterConfigurationSpec rabbitmq = new RabbitmqClusterConfigurationSpec();
        rabbitmq.setAdditionalPlugins(strings);
        rabbitmq.setAdditionalConfig("default_pass = guest \n default_user = guest");

        RabbitmqClusterPersistenceSpec persistence = new RabbitmqClusterPersistenceSpec();
        persistence.setStorageClassName("mq");
        persistence.setStorage(new Quantity("5", "Gi"));

        // 组合
        spec.setPersistence(persistence);
        spec.setRabbitmq(rabbitmq);
        rabbitMqCluster.setSpec(spec);
        return rabbitMqCluster;
    }


    /**
     * 注册监听
     * @return
     * @throws IOException
     */
    @RequestMapping("/watch")
    public String watch() throws IOException {
//        String namespace = "default";
//
//        // 这一层封装
//        CustomResourceDefinitionContext crdContext = new CustomResourceDefinitionContext.Builder()
//                .withGroup("cloud.mq.com")
//                .withPlural("rabbitmqcrds")
//                .withScope("Namespaced")
//                .withVersion("v1")
//                .build();
        KubernetesClient client = k8sUtils.getClient();
//
//        client.customResource(crdContext).watch("kid-mq", new Watcher<String>() {
//            @Override
//            public void eventReceived(Action action, String resource) {
//                // 我的逻辑
//                RabbitMqCrd rabbitMqCrd = JSONObject.parseObject(resource, RabbitMqCrd.class);
//
//
//                log.info("#######我的逻辑########");
//                log.info(resource);
//            }
//
//            @Override
//            public void onClose(KubernetesClientException e) {
//                log.info("#######结束########");
//                if (e != null) {
//                    log.error(e.getMessage(), e);
//                }
//            }
//        });


        client.namespaces().create(new NamespaceBuilder().withNewMetadata().withName("thisisatest").endMetadata().build());


        return "ok";
    }

    @RequestMapping("/init")
    public String init() {
        KubernetesClient client = k8sUtils.getClient();
        NamespaceList list = client.namespaces().list();
        ServiceList myServices = client.services().list();
        ServiceList myNsServices = client.services().inNamespace("default").list();
        System.out.println("NamespaceList --- " + list);
        System.out.println("myServices --- " + myServices);
        System.out.println("myNsServices --- " + myNsServices);
        return "ok";
    }



    /**
     * 创建 crd  资源
     * @return
     */
    @RequestMapping("/init2")
    public String init2() throws IOException, InterruptedException {
        return create();
    }

    public void createPv() {

//        KubernetesClient client = new DefaultKubernetesClient(config);
        KubernetesClient client = k8sUtils.getClient();

        PersistentVolume pv = new PersistentVolumeBuilder()
                .withNewMetadata()
                .withName("pv-demo")
                .withLabels(Collections.singletonMap("name", "xl"))
                .endMetadata()
                .withNewSpec()
                .withPersistentVolumeReclaimPolicy("Retain")
                .endSpec()
                .build();
    }

    private String create() throws IOException, InterruptedException {
        final CountDownLatch closeLatch = new CountDownLatch(1);
        String namespace = "default";
//        KubernetesClient client = new DefaultKubernetesClient(config);
        KubernetesClient client = k8sUtils.getClient();

        CustomResourceDefinition crd = new CustomResourceDefinitionBuilder()
                .withApiVersion("apiextensions.k8s.io/v1beta1")
                .withNewMetadata()
                .withName("itests.examples.fabric8.io")
                .endMetadata()
                .withNewSpec()
                .withGroup("examples.fabric8.io")
                .withVersion("v1")
                .addAllToVersions(Collections.singletonList(new CustomResourceDefinitionVersionBuilder()
                        .withName("v1")
                        .withServed(true)
                        .withStorage(true)
                        .build()))
                .withScope("Namespaced")
                .withNewNames()
                .withPlural("itests")
                .withSingular("itest")
                .withKind("Itest")
                .withShortNames("it")
                .endNames()
                .endSpec()
                .build();

        // 创建crd的核心方法
//        client.customResourceDefinitions().create(crd);

//        Map<String, String> map = new HashMap<>();
//        map.put("name", "xl");
//        map.put("age", "22");
//        map.put("height", "180");
//        Pod pod = new PodBuilder()
//                .withNewMetadata().withName("myapp-pod").withLabels(map).endMetadata()
//                .withNewSpec()
//                .addNewContainer()
//                .withName("myapp-container")
//                .withImage("nginx:latest")
//                .withCommand("sh", "-c", "echo The app is running!; sleep 10")
//                .endContainer()
//                .endSpec()
//                .build();
//
//        // 创建pod
//        client.pods().inNamespace("default").create(pod);
//
//
//        // 监听 watch
//        CustomResourceDefinitionContext crdContext = new CustomResourceDefinitionContext.Builder()
//                .withGroup("examples.fabric8.io")
//                .withPlural("itests")
//                .withScope("Namespaced")
//                .withVersion("v1")
//                .build();
////        client.customResourceDefinitions().create(crd);
//
//        Map<String, Object> list = client.customResource(crdContext).list(namespace);

//        client.customResource(crdContext).watch(namespace, new Watcher<String>() {
//            @Override
//            public void eventReceived(Action action, String resource) {
//                // 你的逻辑
//                log.info("#######我的逻辑########");
//            }
//
//            @Override
//            public void onClose(KubernetesClientException e) {
//                log.info("#######结束########");
//                closeLatch.countDown();
//                if (e != null) {
//                    log.error(e.getMessage(), e);
//                }
//            }
//        });
//
//        closeLatch.await(10, TimeUnit.MINUTES);
        return "ok";
    }








}
