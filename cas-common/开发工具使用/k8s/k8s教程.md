### UI验证登陆
    kubectl -n kubernetes-dashboard describe secret $(kubectl -n kubernetes-dashboard get secret | grep kubernetes-dashboard-token | awk '{print $1}')

### k8s 升级
    http://blog.itpub.net/25854343/viewspace-2660612/
