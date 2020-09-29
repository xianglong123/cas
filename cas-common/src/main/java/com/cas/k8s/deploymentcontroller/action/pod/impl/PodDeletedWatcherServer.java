package com.cas.k8s.deploymentcontroller.action.pod.impl;

import com.cas.k8s.deploymentcontroller.action.pod.PodAddedWatcher;
import com.cas.k8s.deploymentcontroller.action.pod.PodDeletedWatcher;
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
public class PodDeletedWatcherServer extends PodDeletedWatcher {

    public PodDeletedWatcherServer(UnifiedPodWatcher unifiedPodWatcher) {
        this.unifiedPodWatcher = unifiedPodWatcher;
//        this.unifiedPodWatcher.attachDel(this);
    }

    @Override
    public void onPodDeleted(Pod pod) {
        System.out.println("del 被调用");
    }
}
