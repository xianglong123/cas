package com.cas.k8s.deploymentcontroller.action.pod.impl;

import com.cas.k8s.deploymentcontroller.action.pod.PodModifiedWatcher;
import com.cas.k8s.deploymentcontroller.action.pod.UnifiedPodWatcher;
import io.fabric8.kubernetes.api.model.Pod;
import org.springframework.stereotype.Service;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 09:00 2020-09-09
 * @version: V1.0
 * @review:
 */
@Service
public class PodModifiedWatcherServer extends PodModifiedWatcher {

    public PodModifiedWatcherServer(UnifiedPodWatcher unifiedPodWatcher) {
        this.unifiedPodWatcher = unifiedPodWatcher;
//        this.unifiedPodWatcher.attachModify(this);
    }

    @Override
    public void onPodModified(Pod pod) {

        System.out.println("modify 被调用");

    }
}
