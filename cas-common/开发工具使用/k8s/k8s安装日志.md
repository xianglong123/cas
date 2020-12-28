#### 日志

    Set up mutual parsing of system host name and Host file
    设置系统主机名以及host问津的相互解析
    
    Hostnamectl	Set-hostname
    
    Install Dependency Packages
    安装依赖包
    
    yum install -y conntrack ntpdate ntp ipvsadmipset jq iptables curl sysstat libseccomp wget vim net-tools git
    
    Set firewall to Iptables and set empty rule
    设置防火墙为iptables,并设置空规则
    
    systemctl stop firewalld
    && systemctl disable firewalld
    && yum -y install iptables-services
    && systemctl start iptables
    && systemctl enable iptables
    && iptables -F && service iptables save
    
    CloseSELINUX
    
    
    
    swapoff -a && sed -i '/ swap / s/^\(.*\)$/#\1/g' /etc/fstab
    
    setenforce 0 && sed -i 's/^SELINUX=.*/SELINUX=disabled/' /etc/selinux/config
    
    Adjust kernel parameters forK8S
    
    
    
    cat > kubernetes.conf << EOF
    net.bridge.bridge-nf-call-iptables = 1
    net.bridge.bridge-nf-call-ip6tables = 1
    net.ipv4.ip_forward=1
    net.ipv4.tcp_tw_recycle=0
    vm.swappiness=0 # Prohibition of useSwap Space, only when the systemOOM It is allowed to be used only when
    vm.overcommit_memory=1 # Do not check if physical memory is sufficient
    vm.panic_on_oom=0 # OpenOOM
    fs.inotify.max_user_instances=8192
    fs.inotify.max_user_watches=1048576
    fs.file-max=52706963
    fs.nr_open=52706963
    net.ipv6.conf.all.disable_ipv6=1
    net.netfilter.nf_conntrack_max=2310720
    EOF
    
    cp kubernetes.conf	/etc/sysctl.d/kubernetes.conf
    
    sysctl -p /etc/sysctl.d/kubernetes.conf
    
    
    Adjust system time zone
    
    
    #Set the system time zone to China/Shanghai
    
    Timedatectl set-timezone Asia/Shanghai
    
    #Sets the currentUTC Time Write Hardware ClockTimedatectl set-local-rtc 0
    
    #Restart services that depend on system time Systemctl restart rsyslog systemctl restart crond
    
    Shutting down the system does not require services
    
    
    
    systemctl stop postfix && systemctl disable postfix
    
    SettingsRsyslogd AndSystemd journald
    
    mkdir /var/log/journal # Persists the directory where the log is saved
    
    mkdir /etc/systemd/journald.conf.d
    
    cat > /etc/systemd/journald.conf.d/99-prophet.conf << EOF
    #Persistent Save to Disk
    storage=persistent
    #Compress History Log
    compress=yes
    syncIntervalSec=5m
    rateLimitInterval=30s
    rateLimitBurst=1000
    #Maximum occupied space10G
    systemMaxUse=10G
    #Single Journal File maximum200M
    systemMaxFileSize=200M
    #Log Save Time2 Zhou
    maxRetentionSec=2week
    #Do not forward logs toSyslog
    forwardToSyslog=No
    EOF
    
    systemctl restart systemd-journald
    
    Upgrade the system kernel to4.44
    升级系统内核为4.44
    
    
    CentOS 7. X System-built3.10. X There are some kernelsBugs Causing the running of theDocker ,Kubernetes Instability, e.g.:Rpm-Uvh http://www.elrepo.org/elrepo-release-7.0-3.el7.elrepo.noarch.rpm
    
    rpm -Uvh http://www.elrepo.org/elrepo-release-7.0-3.el7.elrepo.noarch.rpm
    
    #Check after installation/boot/grub2/grub.cfg Corresponding kernel inMenuentry Does theInitrd16 Configuration, if not, install it again!
    
    yum --enablerepo=elrepo-kernel install -y kernel-lt
    
    #Set Boot Boot from New Kernel
    设置开机从新内核启动
    
    grub2-set-default 'CentOS Linux (4.4.234-1.el7.elrepo.x86_64) 7 (Core)'
    
    kube-proxy 开启ipvs的前置条件
    modprobe br_netfilter
    cat > /etc/sysconfig/modules/ipvs.modules <<EOF
    #!/bin/bash
    modprobe -- ip_vs
    modprobe -- ip_vs_rr
    modprobe -- ip_vs_wrr
    modprobe -- ip_vs_sh
    modprobe -- nf_conntrack_ipv4
    EOF
    chmod 755 /etc/sysconfig/modules/ipvs.modules
    && bash /etc/sysconfig/modules/ipvs.modules && lsmod | grep -e ip_vS -e nf_conntrack_ipv4
    
    
    安装docker软件
    yum install -y yum-utils device-mapper-persistent-data lvm2
    
    yum-config-manager \
    --add-repo \
    http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
    
    yum update -y && yum install -y docker-ce
    
    
    配置docker开机自启
    systemctl start docker
    systemctl enable docker
    
    
    配置 daemon
    cat > /etc/docker/daemon.json <<EOF
    {
    "exec-opts":["native.cgroupdriver=systemd"],
    "log-driver":"json-file",
    "log-opts":{
    "max-size":"100m"
    }
    }
    EOF
    mkdir -p /etc/systemd/system/docker.service.d
    
    
    重启 docker服务
    systemctl daemon-reload && systemctl restart docker && systemctl enable docker
    
    安装kubeadm(主从配置)
    cat <<EOF > /etc/yum.repos.d/kubernetes.repo
    [kubernetes]
    name=ubernetes
    baseurl=http://mirrors.aliyun.com/kubernetes/yum/repos/kubernetes-el7-x86_64
    enabled=1
    gpgcheck=0
    repo_gpgcheck=0
    gpgkey=http://mirrors.aliyun.com/kubernetes/yum/doc/yum-key.gpg
    http://mirrors.aliyun.com/kubernetes/yum/doc/rpm-package-key.gpg
    EOF
    
    
    yum -y install kubeadm-1.15.1 kubectl-1.15.1 kubelet-1.15.1
    yum -y install kubeadm-1.17.0 kubectl-1.17.0 kubelet-1.17.0
    yum -y uninstall kubeadm kubectl kubelet
    yum -y remove kubeadm kubectl kubelet
    systemctl enable kubelet.service
    
    
    将kubadm-basic和load-images.sh上传到其他节点的/root
    并运行./load-images.sh
    
    
    初始化主节点
    kubeadm config print init-defaults > kubeadm-config.yaml
    
    apiVersion: kubeproxy.config.k8s.io/v1alpha1
    kind: KubeProxyConfiguration
    featureGates :
    SupportIPVSProxyMode: true
    mode: ipvs
    
    高可用配置
    kubeadm init --config=kubeadm-config.yaml --experimental-upload-certs | tee kubeadm-init.log
    kubeadm init --config=kubeadm-config.yaml | tee kubeadm-init.log
    
    
    kubeadm init \
    --config=kubeadm-config.yaml  \
    --image-repository registry.aliyuncs.com/google_containers | tee kubeadm-init.log
    
    ################################################################
    [init] Using Kubernetes version: v1.15.1
    [preflight] Running pre-flight checks
    [WARNING SystemVercication]: this Docker version is not on the list of validated versions: 19.03.12. Latest validated version: 18.09
    [preflight] Pulling images required for setting up a Kubernetes cluster
    [preflight] This might take a minute or two, depending on the speed of your internet connection
    [preflight] You can also perform this action in beforehand using 'kubeadm config images pull'
    [kubelet-start] Writing kubelet environment file with flags to file "/var/lib/kubelet/kubeadm-flags.env"
    [kubelet-start] Writing kubelet configuration to file "/var/lib/kubelet/config.yaml"
    [kubelet-start] Activating the kubelet service
    [certs] Using certificateDir folder "/etc/kubernetes/pki"
    [certs] Generating "ca" certificate and key
    [certs] Generating "apiserver" certificate and key
    [certs] apiserver serving cert is signed for DNS names [k8s-master kubernetes kubernetes.default kubernetes.default.svc kubernetes.default.svc.cluster.local] and IPs [10.96.0.1 192.168.56.104]
    [certs] Generating "apiserver-kubelet-client" certificate and key
    [certs] Generating "front-proxy-ca" certificate and key
    [certs] Generating "front-proxy-client" certificate and key
    [certs] Generating "etcd/ca" certificate and key
    [certs] Generating "etcd/peer" certificate and key
    [certs] etcd/peer serving cert is signed for DNS names [k8s-master localhost] and IPs [192.168.56.104 127.0.0.1 ::1]
    [certs] Generating "apiserver-etcd-client" certificate and key
    [certs] Generating "etcd/server" certificate and key
    [certs] etcd/server serving cert is signed for DNS names [k8s-master localhost] and IPs [192.168.56.104 127.0.0.1 ::1]
    [certs] Generating "etcd/healthcheck-client" certificate and key
    [certs] Generating "sa" key and public key
    [kubeconfig] Using kubeconfig folder "/etc/kubernetes"
    [kubeconfig] Writing "admin.conf" kubeconfig file
    [kubeconfig] Writing "kubelet.conf" kubeconfig file
    [kubeconfig] Writing "controller-manager.conf" kubeconfig file
    [kubeconfig] Writing "scheduler.conf" kubeconfig file
    [control-plane] Using manifest folder "/etc/kubernetes/manifests"
    [control-plane] Creating static Pod manifest for "kube-apiserver"
    [control-plane] Creating static Pod manifest for "kube-controller-manager"
    [control-plane] Creating static Pod manifest for "kube-scheduler"
    [etcd] Creating static Pod manifest for local etcd in "/etc/kubernetes/manifests"
    [wait-control-plane] Waiting for the kubelet to boot up the control plane as static Pods from directory "/etc/kubernetes/manifests". This can take up to 4m0s
    [apiclient] All control plane components are healthy after 37.504108 seconds
    [upload-config] Storing the configuration used in ConfigMap "kubeadm-config" in the "kube-system" Namespace
    [kubelet] Creating a ConfigMap "kubelet-config-1.15" in namespace kube-system with the configuration for the kubelets in the cluster
    [upload-certs] Storing the certificates in Secret "kubeadm-certs" in the "kube-system" Namespace
    [upload-certs] Using certificate key:
    8b335fc331a1dcf554b2ab6a8249a8d929bdb3b20e1c71ba570796365117b3f2
    [mark-control-plane] Marking the node k8s-master as control-plane by adding the label "node-role.kubernetes.io/master=''"
    [mark-control-plane] Marking the node k8s-master as control-plane by adding the taints [node-role.kubernetes.io/master:NoSchedule]
    [bootstrap-token] Using token: abcdef.0123456789abcdef
    [bootstrap-token] Configuring bootstrap tokens, cluster-info ConfigMap, RBAC Roles
    [bootstrap-token] configured RBAC rules to allow Node Bootstrap tokens to post CSRs in order for nodes to get long term certificate credentials
    [bootstrap-token] configured RBAC rules to allow the csrapprover controller automatically approve CSRs from a Node Bootstrap Token
    [bootstrap-token] configured RBAC rules to allow certificate rotation for all node client certificates in the cluster
    [bootstrap-token] Creating the "cluster-info" ConfigMap in the "kube-public" namespace
    [addons] Applied essential addon: CoreDNS
    [addons] Applied essential addon: kube-proxy
    
    Your Kubernetes control-plane has initialized successfully!
    
    To start using your cluster, you need to run the following as a regular user:
    
    mkdir -p $HOME/.kube
    sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
    sudo chown $(id -u):$(id -g) $HOME/.kube/config
    
    You should now deploy a pod network to the cluster.
    Run "kubectl apply -f [podnetwork].yaml" with one of the options listed at:
    https://kubernetes.io/docs/concepts/cluster-administration/addons/
    
    Then you can join any number of worker nodes by running the following on each as root:
    
    kubeadm join 192.168.56.104:6443 --token abcdef.0123456789abcdef \
    --discovery-token-ca-cert-hash sha256:24e430ed3e9ea6b28d8580917d1ff3dd8eff6abf65fea63236c8a96972b7055a
    
    ################################################################
    
    
    集群开始命令
    mkdir -p $HOME/.kube
    sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
    sudo chown $(id -u):$(id -g) $HOME/.kube/config
    
    部署网络
    kubectl apply -f
    wget https://raw.githubusercontent.com/coreos/flannel/master/Documentation/kube-flannel.yml
    
    
    kubectl get pod -n kube-system
    -n:命名空间
    
    kubectl get node
    
    
    Harbor的安装
    
    先在/etc/docker/daemon.json 加假证书："insecure-registries":["https://hub.atguigu.com"]
    
    
    yum -y install lrzsz
    
    
    创建https证书以及配置相关目录
    openssl genrsa -des3 -out server.key 2048
    创建证书
    openssl req -new -key server.key -out server.csr
    退出密码
    openssl rsa -in server.key.org -out server.key
    openssl x509 -req -days 365 -in server.csr -signkey server.key -out server.crt
    
    
    docker命令收集
    1.登录Harbor -- docker login [网址]
    2.拉取镜像 -- docker pull [资源名][:tag]
    3.上传镜像 -- docker push [资源名][:tag]
    4.给资源打tag -- docker tag [资源名][:tag] [资源名][:tag]
    5.删除镜像 -- docker rmi -f [资源名][:tag]
    
    k8s命令收集
    1.拉取镜像并运行指定副本数为1 -- kubectl run redis-deployment --image=hub.atguigu.com/library/redis:latest --port=80 --replicas=1
    2.查看运行的副本 -- [kubectl get deployment] | [kubectl get rs] | [kubectl get pod -o wide]
    3.重置端口（[ERROR Port-10250]: Port 10250 is in use） -- kubeadm reset
    4.修改期望副本数 -- kubectl scale --replicas=3 deployment/[podName]
    5.查看k8s的pod日志 -- kubectl log [name] -c [containerName]
    6.删除svc -- kubectl delete svc [svcName]
    7.删除所有pod -- kubectl delete pod --all
    8.进入容器 -- kubectl exec [podName] -it --/bin/sh
    9.更新镜像 -- kubectl set image deployment/[name] [name][:tag]
    10.回滚镜像 -- kubectl rollout undo deployment/[name]
    11.查看回滚状态 -- kubectl rolleout status deployment/[name]
    12.查看回滚历史 -- kubectl rolleout history deployment/[name]
    13.回滚到指定版本 -- kubectl rollout undo deployment/nginx-deployment --to-revision = 1
    14.指定部署端口(创建SVC) -- kubectl expose deployment nginx-deployment --port=30000 --target-port=80
    15.修改端口(修改svc为NodePort状态，给主机暴露端口) -- kubectl edit svc nginx-deployment
    16.查看kube-proxy状态 --   822  systemctl daemon-reload
    823  systemctl status kube-proxy
    824  systemctl restart kube-proxy
    17.查看端口使用情况 -- netstat -anpt| grep 31544
    18.kubectl run operator-deployment --image=hub.atguigu.com/library/operator:v1 --port=80 --replicas=1
    19.查看节点详情：kubectl describe node k8s-master
    20.设置污点:
    NoSchedule : 表示k8s不会将pod调度到具有该污点的NOde上
    PreferNoSchedule:表示k8s将尽量避免将pod调度到具有该污点的Node上
    NoExecute:表示k8s将不会将pod调度到具有该污点的Node上，同时会将Node上已经存在的POd驱逐出去: 未找到命令
    21.清除污点：kubectl taint nodes k8s-node2 check=xianglong:NoExecute-
    22.查找污点：kubectl describe node k8s-node2 | grep Taints
    23.查看pod和所在namespace:kubectl get pod -A -o yaml |grep '^    n'|grep -v nodeSelector|awk 'NR%3==1{print ++n"\n"$0;next}1'
    24.修改标签 kubectl label pod [podName] [value=:] --overwrite=true
    25.带操作命令日志的创建 kubectl create -f [rs.yaml] --record
    26.修改镜像 kubectl set image deployment/[deploymentName] [containerName=[rs][:tag]]
    27.版本回滚到上一版本 kubectl rollout undo deployment/[deploymentName]
    28.查看回滚状态 kubectl rollout status deployment/[deploymentName]
    29.查看历史版本 kubectl rollout history deployment/[deploymentName]
    30.回滚版本到某个指定版本 kubectl rollout undo deployemnt/[deploymentName] --ro-revision=[Revision]
    31.根据文件夹创建configMap kubectl create configmap [name] --from-file [dirPath]
    32.根据文件创建configMap kubectl create configmap [name] --from-file [dirPath]
    33.根据字面量创建configMap kubectl create configmap [name] --from-literal=name=xl
    
    linux防火墙
    1:查看防火状态 --
    systemctl status firewalld
    service  iptables status
    2:暂时关闭防火墙
    systemctl stop firewalld
    service  iptables stop
    3:永久关闭防火墙
    systemctl disable firewalld
    chkconfig iptables off
    4:重启防火墙
    systemctl enable firewalld
    service iptables restart  
    5:永久关闭后重启
    //暂时还没有试过
    chkconfig iptables on
    
    
    签发证书：
    cfssl gencert -ca=ca.pem -ca-key=ca-key.pem -config=ca-config.json -profile=client kube-proxy-csr.json |cfssljson -bare kube-proxy-client
    
    
    
    ### 控制器
    自定义控制器RS：可以控制pod的副本数，自己控制生命周期，命令式控制
    DamenSet:申明式控制，你告诉他你需要什么，他帮你创建，但是他还是借助RS来管理的，可以扩容，缩容，开始和暂停节点，平缓迭代服务
    Job和coreJob:对需要执行命令的用这个
    StatefulSet:k8s大部分容器是无状态的，对于管理有状态的需要用SS管理器，他能确保pod重新调度后podName和HostName不变
    
    
    安装operator-jdk
    RELEASE_VERSION=v1.0.0
    curl -LO https://github.com/operator-framework/operator-sdk/releases/download/v1.0.0/operator-sdk-v1.0.0-x86_64-linux-gnu
    curl -LO https://github.com/operator-framework/operator-sdk/releases/download/v1.0.0/operator-sdk-v1.0.0-x86_64-linux-gnu.asc
    
    
    查看k8s域名：
    [root@k8s-master svc-headless]# kubectl run -it --image=busybox:1.28.4 --rm --restart=Never sh
    If you don't see a command prompt, try pressing enter.
    / # nslookup kubernetes
    Server:    10.96.0.10
    Address 1: 10.96.0.10 kube-dns.kube-system.svc.cluster.local
    
    Name:      kubernetes
    Address 1: 10.96.0.1 kubernetes.default.svc.cluster.local
    / #
    
    
    下载和设置nfs:
    在核心节点上运行这个
    yum install -y  nfs-common nfs-utils rpcbind
    
    在其他节点上运行这个
    yum install -y nfs-utils rpcbind
    
    [root@harbor /]# vim /etc/exports
    [root@harbor /]# chmod 777 nfs1/ nfs2/ nfs3/
    [root@harbor /]# chown nfsnobody nfs1/ nfs2/ nfs3/
    [root@harbor /]# systemctl restart rpcbind
    [root@harbor /]# systemctl restart nfs
    
    测试挂载点：
    1072  showmount -e 192.168.56.102
    1073  mount -t nfs 192.168.56.102:/nfs /test/
    1074  cd /
    1075  mkdir /test
    1076  mount -t nfs 192.168.56.102:/nfs /test/
    1077  umount /usr
    
    umount /data1_11
    
    删除kid-redis命名空间下的资源
    kubectl delete statefulset -n kid-redis --all && kubectl delete pvc -n kid-redis --all && kubectl delete cm -n kid-redis --all && kubectl delete pv --all && kubectl delete deployment -n kid-redis --all
    
    部署redis集群
    kubectl exec -it redis-cluster-0 -n kid-redis -- redis-cli --cluster create --cluster-replicas 1 $(kubectl get pods -n kid-redis -l app=redis-cluster -o jsonpath='{range.items[*]}{.status.podIP}:6379 ')
    kubectl exec -it redis-cluster-0 -n mid-redis -- redis-cli --cluster create --cluster-replicas 1 $(kubectl get pods -n mid-redis -l app=redis-cluster -o jsonpath='{range.items[*]}{.status.podIP}:6379 ')
    
    
    kubectl exec redis-cluster-0 -n kid-redis -it -- /bin/sh
    redis-cli -c -h 127.0.0.1 -p 6379
    kubectl get pods -n kid-redis -o wide | grep -oP 10
    
    无头（headless）服务
    dig -t A myapp-headless.default.svc.cluster.local. @10.244.0.18
    
    
    查看secret:
    kubectl exec kube-proxy-756wt -n kube-system -it -- /bin/sh
    cd /run/secrets/kubernetes.io/serviceaccount
    
    
    #####----------------------------------------kubeconfig创建(start)-------------------#######
    
    创建证书：
    [root@k8s-master devuser]# cat devuser-csr.json
    {
    "CN": "devuser",
    "hosts": [],
    "key": {
    "algo": "rsa",
    "size": 2048
    },
    "names": [
    {
    "C": "CN",
    "ST": "BeiJing",
    "L": "BeiJing",
    "O": "k8s",
    "OU": "System"
    }
    ]
    }
    
    
    scp * root@192.168.56.101:/usr/local/bin
    chmod a+x *
    
    [root@k8s-master devuser]# cd /etc/kubernetes/pki/
    [root@k8s-master pki]# ls
    apiserver.crt                 apiserver-kubelet-client.key  front-proxy-ca.key
    apiserver-etcd-client.crt     ca.crt                        front-proxy-client.crt
    apiserver-etcd-client.key     ca.key                        front-proxy-client.key
    apiserver.key                 etcd                          sa.key
    apiserver-kubelet-client.crt  front-proxy-ca.crt            sa.pub
    [root@k8s-master pki]# cfssl gencert -ca=ca.crt -ca-key=ca.key -profile=kubernetes /usr/local/install-k8s/devuser-csr.json | cfssljson -bare devuser
    cert/   core/   igress/ plugin/
    [root@k8s-master pki]# cfssl gencert -ca=ca.crt -ca-key=ca.key -profile=kubernetes /usr/local/install-k8s/cert/devuser/devuser-csr.json | cfssljson -bare devuser
    2020/09/02 20:34:08 [INFO] generate received request
    2020/09/02 20:34:08 [INFO] received CSR
    2020/09/02 20:34:08 [INFO] generating key: rsa-2048
    2020/09/02 20:34:08 [INFO] encoded CSR
    2020/09/02 20:34:08 [INFO] signed certificate with serial number 339997240650182000887731858061164778807406567287
    2020/09/02 20:34:08 [WARNING] This certificate lacks a "hosts" field. This makes it unsuitable for
    websites. For more information see the Baseline Requirements for the Issuance and Management
    of Publicly-Trusted Certificates, v.1.1.6, from the CA/Browser Forum (https://cabforum.org);
    specifically, section 10.2.3 ("Information Requirements").
    
    ## 设置主机ip
    export KUBE_APISERVER="https://192.168.56.101:6443"
    
    ## 设置集群参数
    kubectl config set-cluster kubernetes --certificate-authority=/etc/kubernetes/pki/ca.crt --embed-certs=true --server=${KUBE_APISERVER} --kubeconfig=devuser.kubeconfig
    
    ## 设置客户端认证参数
    [root@k8s-master devuser]# kubectl config set-credentials devuser  --client-certificate=/etc/kubernetes/pki/devuser.pem  --client-key=/etc/kubernetes/pki/devuser-key.pem  --embed-certs=true  --kubeconfig=devuser.kubeconfig
    
    ## 设置上下文参数
    [root@k8s-master devuser]# kubectl config set-context kubernetes  --cluster=kubernetes  --user=devuser  --namespace=dev  --kubeconfig=devuser.kubeconfig
    
    
    kubectl create rolebinding devuser-admin-binding --clusterrole=admin --user=devuser --namespace=dev
    
    ## 设置文件归属权限
    chown devuser:devuser /home/devuser/.kube/devuser.kubeconfig
    
    [root@k8s-master devuser]# pwd
    /usr/local/install-k8s/cert/devuser
    [root@k8s-master devuser]# cp devuser.kubeconfig /home/devuser/.kube/
    
    ## 指定配置
    kubectl config use-context kubernetes --kubeconfig=config
    
    
    #####----------------------------------------kubeconfig创建(end)-------------------#######
    
    kubernetes.default.svc.cluster.local
    
    
    
    
