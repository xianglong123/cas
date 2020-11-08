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
