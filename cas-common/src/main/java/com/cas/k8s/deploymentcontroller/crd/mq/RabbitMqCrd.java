package com.cas.k8s.deploymentcontroller.crd.mq;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.OwnerReferenceBuilder;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.api.model.apiextensions.CustomResourceDefinition;
import io.fabric8.kubernetes.api.model.apiextensions.CustomResourceDefinitionBuilder;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.client.dsl.base.CustomResourceDefinitionContext;
import lombok.Data;

import java.util.UUID;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 16:38 2020-09-08
 * @version: V1.0
 * @review:
 */
@Data
public class RabbitMqCrd extends CustomResource implements Namespaced, KubernetesResource {
    public static final String CRD_GROUP = "rabbitmq.com";
    public static final String CRD_SINGULAR_NAME = "rabbitmqcluster";
    public static final String CRD_PLURAL_NAME = "rabbitmqclusters";
    public static final String CRD_NAME = CRD_PLURAL_NAME + "." + CRD_GROUP;
    public static final String CRD_KIND = "RabbitMqCrd";
    public static final String CRD_SCOPE = "Namespaced";
    public static final String CRD_SHORT_NAME = "mq";
    public static final String CRD_VERSION = "v1beta1";
    public static final String CRD_API_VERSION = "apiextensions.k8s.io/v1beta1";


    public static CustomResourceDefinition RABBITMQ_CRD = new CustomResourceDefinitionBuilder()
            .withApiVersion(CRD_API_VERSION)
            .withNewMetadata()
            .withName(CRD_NAME)
            .endMetadata()


            .withNewSpec()
            .withGroup(CRD_GROUP)
            .withVersion(CRD_VERSION)
            .withScope(CRD_SCOPE)
            .withNewNames()
            .withKind(CRD_KIND)
            .withShortNames(CRD_SHORT_NAME)
            .withSingular(CRD_SINGULAR_NAME)
            .withPlural(CRD_PLURAL_NAME)
            .endNames()
            .endSpec()

            .withNewStatus()
            .withNewAcceptedNames()
            .addToShortNames(new String[]{"availableReplicas", "replicas", "updatedReplicas"})
            .endAcceptedNames()
            .endStatus()
            .build();

    /**
     * todo 这里用于监控，正文不用，留着测试，正文删除
     */
    public static CustomResourceDefinitionContext RABBITMQ_CONTEXT = new CustomResourceDefinitionContext.Builder()
            .withGroup(CRD_GROUP)
                .withPlural(CRD_PLURAL_NAME)
                .withScope(CRD_SCOPE)
                .withVersion(CRD_VERSION)
                .build();


    // 核心数据
    @JsonProperty("spec")
    private RabbitMqCrdSpec spec;


    @Override
    public ObjectMeta getMetadata() {
        return super.getMetadata();
    }


    public Pod createPod() {
        int hashCode = this.getSpec().getTemplate().hashCode();
        Pod pod = new PodBuilder()
                .withNewMetadata()
                .withLabels(this.getSpec().getSelector().getMatchLabels())
                .addToLabels("pod-template-hash", String.valueOf(hashCode > 0 ? hashCode : -hashCode))
                .withName(this.getMetadata().getName()
                        .concat("-")
                        .concat(UUID.randomUUID().toString()))
                .withNamespace(this.getMetadata().getNamespace())
                .withOwnerReferences(
                        new OwnerReferenceBuilder()
                                .withApiVersion(this.getApiVersion())
                                .withController(Boolean.TRUE)
                                .withBlockOwnerDeletion(Boolean.TRUE)
                                .withKind(this.getKind())
                                .withName(this.getMetadata().getName())
                                .withUid(this.getMetadata().getUid())
                                .build()
                )
                .withUid(UUID.randomUUID().toString())
                .endMetadata()
                .withSpec(this.getSpec().getTemplate().getSpec())
                .build();
        return pod;
    }


}
