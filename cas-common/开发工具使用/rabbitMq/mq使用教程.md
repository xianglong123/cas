### mq使用教程
    https://blog.csdn.net/qq_35387940/article/details/100514134
    
### 停止mq
    sudo rabbitmqctl stop

### 启动mq服务
    sudo rabbitmq-server 
    
### 后台运行
    sudo rabbitmq-server -detached
    
### 加入集群
    rabbitmqctl join_cluster rabbit@cssw2xx54113reg
    
### 删除集群（将cloud05从cloud03集群中剔除）
    rabbitmqctl  -n rabbit@cloud05  forget_cluster_node rabbit@cloud03
