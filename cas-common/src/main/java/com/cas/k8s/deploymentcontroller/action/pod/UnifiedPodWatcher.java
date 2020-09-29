package com.cas.k8s.deploymentcontroller.action.pod;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodCondition;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UnifiedPodWatcher {

    private static final String POD_READY = "Ready";
    private static final String NODE_READY = "Ready";
    private static final String TRUE = "True";

    @Autowired
    private PodAddedWatcher podAddedWatcher;

    @Autowired
    private PodModifiedWatcher podModifiedWatcher;

    @Autowired
    private PodDeletedWatcher podDeletedWatcher;

//    private List<PodAddedWatcher> podAddedWatchers = new ArrayList<>();
//
//    private List<PodModifiedWatcher> podModifiedWatchers = new ArrayList<>();
//
//    private List<PodDeletedWatcher> podDeletedWatchers = new ArrayList<>();

//    public void attachAdd(PodAddedWatcher podAddedWatcher) {
//        podAddedWatchers.add(podAddedWatcher);
//    }
//
//    public void attachModify(PodModifiedWatcher podModifiedWatcher) {
//        podModifiedWatchers.add(podModifiedWatcher);
//    }
//
//    public void attachDel(PodDeletedWatcher podDeletedWatcher) {
//        podDeletedWatchers.add(podDeletedWatcher);
//    }


    /**
     * 将Pod事件统一收到此处
     *
     * @param action
     * @param pod
     */
    public void eventReceived(Watcher.Action action, Pod pod) {
        log.info("Thread {}: PodWatcher: {} =>  {}, {}", Thread.currentThread().getId(), action, pod.getMetadata().getName(), pod);
        switch (action) {
            case ADDED:
                podAddedWatcher.onPodAdded(pod);
                break;
            case MODIFIED:
                podModifiedWatcher.onPodModified(pod);
                break;
            case DELETED:
                podDeletedWatcher.onPodDeleted(pod);
                break;
            default:
                break;
        }
    }

    public static boolean isPodReady(Pod pod) {
        Utils.checkNotNull(pod, "Pod can't be null.");
        PodCondition condition = getPodReadyCondition(pod);

        //Can be true in testing, so handle it to make test writing easier.
        if (condition == null || condition.getStatus() == null) {
            return false;
        }
        return condition.getStatus().equalsIgnoreCase(TRUE);
    }

    /**
     * Returns the ready condition of the pod.
     *
     * @param pod The target pod.
     * @return The {@link PodCondition} or null if not found.
     */
    private static PodCondition getPodReadyCondition(Pod pod) {
        Utils.checkNotNull(pod, "Pod can't be null.");

        if (pod.getStatus() == null || pod.getStatus().getConditions() == null) {
            return null;
        }

        for (PodCondition condition : pod.getStatus().getConditions()) {
            if (POD_READY.equals(condition.getType())) {
                return condition;
            }
        }
        return null;
    }

}
