## nginx 案例
    https://www.cnblogs.com/jpfss/p/10232980.html
    location = / {
       #规则A
    }
    location = /login {
       #规则B
    }
    location ^~ /static/ {
       #规则C
    }
    location ~ \.(gif|jpg|png|js|css)$ {
       #规则D，注意：是根据括号内的大小写进行匹配。括号内全是小写，只匹配小写
    }
    location ~* \.png$ {
       #规则E
    }
    location !~ \.xhtml$ {
       #规则F
    }
    location !~* \.xhtml$ {
       #规则G
    }
    location / {
       #规则H
    }
    那么产生的效果如下：
    
    访问根目录/， 比如http://localhost/ 将匹配规则A
    
    访问 http://localhost/login 将匹配规则B，http://localhost/register 则匹配规则H
    
    访问 http://localhost/static/a.html 将匹配规则C
    
    访问 http://localhost/a.gif, http://localhost/b.jpg 将匹配规则D和规则E，但是规则D顺序优先，规则E不起作用， 而 http://localhost/static/c.png 则优先匹配到 规则C
    
    访问 http://localhost/a.PNG 则匹配规则E， 而不会匹配规则D，因为规则E不区分大小写。
    
    访问 http://localhost/a.xhtml 不会匹配规则F和规则G，
    
    http://localhost/a.XHTML不会匹配规则G，（因为!）。规则F，规则G属于排除法，符合匹配规则也不会匹配到，所以想想看实际应用中哪里会用到。
    
    访问 http://localhost/category/id/1111 则最终匹配到规则H，因为以上规则都不匹配，这个时候nginx转发请求给后端应用服务器，比如FastCGI（php），tomcat（jsp），nginx作为方向代理服务器存在。
    

## nginx 在linux 中的错误日志
    tail -50f /var/log/nginx/error.log
    
## 问题
    发送请求，占用了worker的几个连接数？
    答案：2个或者四个，只访问静态资源就一来一回两个，访问后端就多两个连接DB的一来一回
    
    nginx有一个master, 有4个worker，每个worker支持的最大连接数1024，支持的最大并发数是多少
    1. 访问普通静态并发数是： worker_connections * worker_processes / 2
    2. 而如果是HTTP作为反向代理来说，最大并发数量应该是worker_connections * work_processes / 4

## work的工作模式
    woker是真正工作的，采用喂狗式，就是挣抢工作来完成这种模式进行工作
    
## 查看nginx的版本号
    nginx -v

##  启动nginx
    nginx

## 启动nginx 
    nginnx -s stop

## 重新加载配置
    nginx -s reload
    
## nginx 的配置文件
    find / -name nginx.conf
    nginx配置有三个部分组成:
    第一部分 全局块
    从配置文件开始到events 块之间的内容，只要会设置一些影响Nginx服务器整体运行的配置指令
    比如 worker_processes 1; worker_processes值越大，可以支持的并发处理量也越多
    
    第二部分 events块
    worker_connection 1024 ； 支持最大连接数
    
    第三部分 http块
    Nginx 服务器配置中最频繁的部分
    http块内也可以包括 http全局块，server块

    ip_hash:每个请求按访问ip的hash结果分配，这样每个访客固定访问一个后端服务器，可以解决session的问题
    upstream backserver {
        ip_hash;
        server 192.168.0.14:88;
        server 192.168.0.15:80;
    }
    
    fair:按后端服务器的响应时间来分配请求，响应时间短的优先分配
    upstream backserver {
        server server1;
        server server2;
        fair;
    }
    
    url_hash:按访问url的hash结果来分配请求，使每个url定向到同一个后端服务器，后端服务器为缓存时比较有效。
    upstream backserver {
        server squid1:3128;
        server squid2:3128;
        hash $request_uri;
        hash_method crc32;
    }123456
