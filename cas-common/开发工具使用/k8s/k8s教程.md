### UI验证登陆
    kubectl -n kubernetes-dashboard describe secret $(kubectl -n kubernetes-dashboard get secret | grep kubernetes-dashboard-token | awk '{print $1}')

### k8s 升级
    http://blog.itpub.net/25854343/viewspace-2660612/

### mac nfs 问题
    https://lizhiyong2000.github.io/2019/03/30/mac%E4%B8%AD%E9%85%8D%E7%BD%AEnfs-server/
