## 创建rabbitmq
    docker run -d --hostname my-rabbit -p 5672:5672 -p 15672:15672 rabbitmq:3.7.7-management
    docker run -d --hostname localhost --name myrabbit -p 15672:15672 -p 5672:5672 rabbitmq:3.7.7-management