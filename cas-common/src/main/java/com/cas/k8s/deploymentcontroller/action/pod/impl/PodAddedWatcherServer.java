package com.cas.k8s.deploymentcontroller.action.pod.impl;

import com.cas.k8s.deploymentcontroller.action.pod.PodAddedWatcher;
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
public class PodAddedWatcherServer extends PodAddedWatcher {

    public PodAddedWatcherServer(UnifiedPodWatcher unifiedPodWatcher) {
        this.unifiedPodWatcher = unifiedPodWatcher;
//        this.unifiedPodWatcher.attachAdd(this);
    }

    @Override
    public void onPodAdded(Pod pod) {
        // 真正执行增加pod的逻辑





        System.out.println("被调用....");


    }
}
