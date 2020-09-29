package com.cas.k8s.deploymentcontroller.action.pod;

import io.fabric8.kubernetes.api.model.Pod;
import org.springframework.stereotype.Service;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 16:43 2020-09-08
 * @version: V1.0
 * @review:
 */
public abstract class PodDeletedWatcher {

    protected UnifiedPodWatcher unifiedPodWatcher;

    public abstract void onPodDeleted(Pod pod);
}
