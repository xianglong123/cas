### UI验证登陆
    kubectl -n kubernetes-dashboard describe secret $(kubectl -n kubernetes-dashboard get secret | grep kubernetes-dashboard-token | awk '{print $1}')

### k8s 升级
    http://blog.itpub.net/25854343/viewspace-2660612/

### mac nfs 问题
    https://lizhiyong2000.github.io/2019/03/30/mac%E4%B8%AD%E9%85%8D%E7%BD%AEnfs-server/

### 强制删除pod
    k delete po admin-rabbitmq-server-0 -n admin-mq --force --grace-period=0
    
### 强制删除不好用，一直处于 Terminating 状态使用下面的代码
    k patch [pv/pvc] [pvName/pvcName]  -p '{"metadata":{"finalizers":null}}' -n mid-redis
    
### 切换命名空间
    kubens [namespace]
    
### 强制删除crd
    # remove the CRD finalizer blocking on custom resource cleanup
    kubectl patch crd/crontabs.stable.example.com -p '{"metadata":{"finalizers":[]}}' --type=merge
    
    # now the CRD can be deleted (orphaning custom resources in etcd)
    kubectl delete -f crd.yaml 
    customresourcedefinition.apiextensions.k8s.io "crontabs.stable.example.com" deleted
    Error from server (NotFound): error when deleting "crd.yaml": the server could not find the requested resource (delete crontabs.stable.example.com my-new-cron-object)
    
    # when the CRD is recreated, it resurfaces the existing custom resources
    kubectl create -f crd.yaml
    customresourcedefinition.apiextensions.k8s.io "crontabs.stable.example.com" created
    Error from server (AlreadyExists): error when creating "crd.yaml": object is being deleted: crontabs.stable.example.com "my-new-cron-object" already exists
    
    # the custom resources can now be edited to remove finalizers
    kubectl patch crontab/my-new-cron-object -p '{"metadata":{"finalizers":[]}}' --type=merge
    crontab.stable.example.com "my-new-cron-object" patched
    
    # and now both custom resource and CRD can be deleted
    kubectl delete -f crd.yaml 
    customresourcedefinition.apiextensions.k8s.io "crontabs.stable.example.com" deleted
    Error from server (NotFound): error when deleting "crd.yaml": crontabs.stable.example.com "my-new-cron-object" not found


### mysql 通过K8s 访问无法输入中文
    通过docker 进入，用这个命令 docker exec -it 8f0404a74e4f env LANG=C.UTF-8 /bin/bash
    
### 删除k8s的节点污点
    kubectl taint node cloud04  node.kubernetes.io/unreachable
    kubectl taint node cloud04 key1=value1:NoSchedule
    kubectl taint nodes cloud03 node.kubernetes.io/unreachable:NoExecute-
    kubectl taint node node1 key1=value1
    node.kubernetes.io/unreachable:NoExecute
    node.kubernetes.io/unreachable:NoSchedule
    node.kubernetes.io/unreachable:NoExecute
    node.kubernetes.io/unreachable:NoSchedule
    
    核心语句：kubectl taint nodes cloud03 node.kubernetes.io/unreachable:NoSchedule-
    备注：在污点的后面加个'-'就可以了
