### Mac安装Mq教程
    https://blog.csdn.net/kangguang/article/details/104551284/?utm_medium=distribute.pc_relevant.none-task-blog-baidujs-1

### 安装命令过程日志
    echo 'Y7NxC/tGJ/HbITFy1dQgB/Tq3hk0sboAhWFPJW9f4=' > /app/rabbitmq/.erlang.cookie


    rabbitmqctl stop_app&&rabbitmqctl reset&&rabbitmqctl join_cluster rabbit@midtest01&&rabbitmqctl start_app
    
    - name: 将其他节点加入主节点
      when:  ansible_hostname != (masterNode)
      shell: 'rabbitmqctl stop_app&&rabbitmqctl reset&&rabbitmqctl join_cluster rabbit@{{ masterNode }}&&rabbitmqctl start_app'
    - name: 添加用户(在主节点上执行)
      ignore_errors: true
      when: ansible_hostname == (masterNode)
      shell: 'rabbitmqctl add_user {{ adminUser }} {{ adminPass }} && rabbitmqctl set_user_tags {{ adminUser }} administrator && rabbitmqctl set_permissions -p / {{ adminUser }} ".*" ".*" ".*"'
    
    10.4.0.75 midtest75  
    10.4.0.76 midtest76  
    10.4.0.77 midtest77  
    10.4.0.78 midtest78
    
    
    rabbitmqctl stop_app&&rabbitmqctl reset&&rabbitmqctl start_app
    
    rpm -Uvh /usr/local/devtoos/erlang-22.3.4.11-1.el7.x86_64.rpm

    
    
    rabbitmqctl join_cluster rabbit@node21
    
    
    rm -rf /var/log/rabbitmq/* && rm -rf /var/lib/rabbitmq/*&&service rabbitmq-server start
    
    service rabbitmq-server start
    service rabbitmq-server stop
    
    
    rm -rf /var/lib/rabbitmq /etc/rabbitmq /var/log/rabbitmq /usr/lib/ocf/resource.d/rabbitmq /usr/lib/rabbitmq /app/rabbitmq /etc/selinux/targeted/active/modules/100/rabbitmq /var/spool/mail/rabbitmq && mkdir /app/rabbitmq && chown rabbitmq:erl /app/rabbitmq
    
    
    mkdir /app/rabbitmq && chown rabbitmq:erl /app/rabbitmq
    
    rabbitmq-plugins enable rabbitmq_management&&systemctl start rabbitmq-server
    rabbitmqctl add_user admin admin && rabbitmqctl set_user_tags admin administrator
    
    
    rpm -e rabbitmq-server-3.8.9-1.el7.noarch
    
    rpm -e rabbitmq-server-3.7.8-1.el7.noarch && ps -ef | grep rabbitmq
    
    rpm -e rabbitmq-server-3.7.8-1.el7.noarch && rm -rf /var/lib/rabbitmq /etc/rabbitmq /var/log/rabbitmq /usr/lib/ocf/resource.d/rabbitmq /usr/lib/rabbitmq /app/rabbitmq /etc/selinux/targeted/active/modules/100/rabbitmq /var/spool/mail/rabbitmq && mkdir /app/rabbitmq && chown rabbitmq:erl /app/rabbitmq
    
    
    rabbitmqctl cluster_status
    
    
    127.0.0.1   localhost localhost.localdomain localhost4 localhost4.localdomain4
    ::1         localhost localhost.localdomain localhost6 localhost6.localdomain6
    192.168.56.101 k8s-master
    192.168.56.105 k8s-node1
    192.168.56.104 k8s-node2
    192.168.56.102 harbor
    
    192.168.56.102 hub.atguigu.com
    151.101.76.133 raw.githubusercontent.com
    140.82.114.4 github.com
    
    {"MQ_host_Info":[{"ipadd":"192.168.56.105","hostname":"k8s-node1"},{"ipadd":"192.168.56.104","hostname":"k8s-node2"},{"ipadd":"192.168.56.101","hostname":"k8s-master"}]}
    
    {"MQ_host_Info":[{"ipadd":"192.168.56.104","hostname":"k8s-node2"},{"ipadd":"192.168.56.105","hostname":"k8s-node1"},{"ipadd":"192.168.56.101","hostname":"k8s-master"}]}
    
    ansible-playbook -i "192.168.56.104,192.168.56.105," -e "masterNode=k8s-node2" /root/rabbitmq_cluster/rabbitmq.yml
    ansible-playbook	-i "192.168.56.104,192.168.56.105,"	-e "masterNode=k8s-node2"		/root/rabbitmq_cluster/rabbitmq.yml
    
    ansible-playbook -i '10.3.60.187,10.3.60.189,' -e 'masterNode=cloud03' -e '{"MQ_host_Info":[{"ipadd":"10.3.60.187","hostname":"cloud03"},{"ipadd":"10.3.60.189","hostname":"cloud05"}]}' rabbitmq.yml
    
    
    ansible node2 -m yum -a 'name=/tmp/rabbtimqSoft/wxGTK-gl-2.8.12-20.el7.x86_64.rpm state=latest'
