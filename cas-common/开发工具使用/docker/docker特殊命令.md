### 查看容器所有者
    docker run -ti --rm --entrypoint="/bin/bash" [imageId] -c "whoami && id"
    
    docker run -ti --rm --entrypoint="/bin/bash" [imageId] -c "ls -l  /var/lib/rabbitmq/mnesia/"
