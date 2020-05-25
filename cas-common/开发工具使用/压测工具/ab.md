### Mac 使用ab命令进行压测
      1.在Mac中配置Apache
      
      ①启动Apache，打开终端
      
       sudo apachectl -v
      如下显示Apache的版本
      
      
      
      sudo apachectl start
      这样Apache就启动了。打开Safari浏览器地址栏输入 “http://localhost”，可以看到内容为“It works!”的页面
      
      
      
      ②设置虚拟端终机
      
      打开Apache的配置文件
      
      sudo vi /etc/apache2/httpd.conf
      在httpd.conf中找到“#Include /private/etc/apache2/extra/httpd-vhosts.conf”，去掉前面的“＃”，保存并退出，去掉这一行的#意思是从/extra/httpd-vhosts.conf这个文件导入虚拟主机配置。
      
      #Include /private/etc/apache2/extra/httpd-vhosts.conf
      然后重启Apache
      
      sudo apachectl restart
      运行如下命令：
      
      sudo vi /etc/apache2/extra/httpd-vhosts.conf
      就打开了配置虚拟主机文件httpd-vhost.conf，配置虚拟主机了。需要注意的是该文件默认开启了两个作为例子的虚拟主机：
      
      <VirtualHost *:80>
      	ServerAdmin webmaster@dummy-host.example.com
      	DocumentRoot "/usr/docs/dummy-host.example.com"
      	ServerName dummy-host.example.com
      	ErrorLog "/private/var/log/apache2/dummy-host.example.com-error_log"
      	CustomLog "/private/var/log/apache2/dummy-host.example.com-access_log" common
      </VirtualHost>
      
      <VirtualHost *:80>
      	ServerAdmin webmaster@dummy-host2.example.com
      	DocumentRoot "/usr/docs/dummy-host2.example.com"
      	ServerName dummy-host2.example.com
      	ErrorLog "/private/var/log/apache2/dummy-host2.example.com-error_log"
      	CustomLog "/private/var/log/apache2/dummy-host2.example.com-access_log" common
      </VirtualHost>
      需要增加如下配置：
      
      <VirtualHost *:80>
      	DocumentRoot "/Library/WebServer/Documents"
      	ServerName localhost
      	ErrorLog "/private/var/log/apache2/localhost-error_log"
      	CustomLog "/private/var/log/apache2/localhost-access_log" common
      </VirtualHost>
      
      <VirtualHost *:80>
      	DocumentRoot "/Users/snandy/work"
      	ServerName mysites
      	ErrorLog "/private/var/log/apache2/sites-error_log"
      	CustomLog "/private/var/log/apache2/sites-access_log" common
      <Directory />
                  Options Indexes FollowSymLinks MultiViews
                  AllowOverride None
                  Order deny,allow
                  Allow from all
        </Directory>
      </VirtualHost>
      保存并退出
      
      :wq
      sudo apachectl restart
      2.配置完成之后进行压测
      
      ab -n 4 -c 2 https://www.baidu.com/
      -n后面的是请求数
      
      -c后面的是并发数
      
      
      
      ①Requests per second 吞吐率
      
      计算公式：总请求数/处理完成这些请求数所花费的时间，即
      Request per second=Complete requests/Time taken for tests
      
      ②Concurrency Level 并发用户数
      
      要注意区分这个概念和并发连接数之间的区别，一个用户可能同时会产生多个会话，也即连接数。在HTTP/1.1下，IE7支持两个并发连接，IE8支持6个并发连接，FireFox3支持4个并发连接，所以相应的，我们的并发用户数就得除以这个基数。
      
      ③Time per request 用户平均请求等待时间
      
      计算公式：处理完成所有请求数所花费的时间/（总请求数/并发用户数），即：
      Time per request=Time taken for tests/（Complete requests/Concurrency Level）
      
      ④Time per request:across all concurrent requests 服务器平均请求等待时间
      
      计算公式：处理完成所有请求数所花费的时间/总请求数，即：
      Time taken for/testsComplete requests