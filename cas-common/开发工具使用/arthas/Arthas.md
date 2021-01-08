### 简介
    Arthas是阿里开源的java运行检测工具

### 运行
    第一步下载: wget https://arthas.aliyun.com/arthas-boot.jar
    
    第二步运行:java -jar arthas-boot.jar

### Dashboard
    dashboard 命令可以查看当前系统的实时数据面板

### Thread
    thread 1 命令会打印线程ID 1的栈。
    Arthas支持管道，可以用thread 1 | grep 'main'查到main class

### Sc
    可以通过 sc 命令来查找JVM里已经加载的类
    sc -d *MathGame

### Jad
    可以通过 jad 命令来反编译代码
    jad demo.MathGame

### Watch
    通过watch命令可以查看函数的参数/返回值/异常信息。
    watch demo.MathGame primeFactors returnObj
    输入 Q 或者 Ctrl+C 退出watch命令。
    在上面的例子里，第三个参数是返回值表达式，它实际上是一个ognl表达式，它支持一些内置对象：
    
    loader
    clazz
    method
    target
    params
    returnObj
    throwExp
    isBefore
    isThrow
    isReturn
    你可以利用这些内置对象来组成不同的表达式。比如返回一个数组：
    
    watch com.example.demo.arthas.user.UserController * '{params[0], target, returnObj}'

### sysprop
    sysprop 可以打印所有的System Properties信息。
    也可以指定单个key： sysprop java.version
    也可以通过grep来过滤： sysprop | grep user
    可以设置新的value： sysprop testKey testValue

    sysenv
    sysenv 命令可以获取到环境变量。和sysprop命令类似。

    jvm
    jvm 命令会打印出JVM的各种详细信息。

    dashboard
    dashboard 命令可以查看当前系统的实时数据面板。
    输入 Q 或者 Ctrl+C 可以退出dashboard命令。

### Ognl
    动态执行static函数
    [arthas@6843]$ ognl '@com.cmft.cloud.utils.StringUtil@isEmpty("aa")'
    @Boolean[false]
    [arthas@6843]$ ognl '@com.cmft.cloud.utils.StringUtil@isEmpty("")'
    @Boolean[true]
    [arthas@6843]$ 

### java热更新
    第一步，将需要更新的类重定向到文本文档
    jad反编译UserController
    jad --source-only com.example.demo.arthas.user.UserController > /tmp/UserController.java
    jad反编译的结果保存在 /tmp/UserController.java文件里了。

    第二步，更新文本文档，保存
    vim /tmp/UserCOntroller.java

    第三步，sc查找加载类的ClassLoader
    $ sc -d *UserController | grep classLoaderHash
    classLoaderHash   1be6f5c3
    
    第四步，mc重新编译，指定classLoader，这里的classLoaderHash就是上面查询到的 1be6f5c3
    mc -c <classLoaderHash> /tmp/UserController.java -d /tmp，使用-c参数指定ClassLoaderHash:
    $ mc -c 1be6f5c3 /tmp/UserController.java -d /tmp

    第五步，redefine 命令重新加载新编译好的UserController.class
    redefine /tmp/com/example/demo/arthas/user/UserController.class
    redefine success, size: 1
