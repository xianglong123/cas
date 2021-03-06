### redis 简介
简单来说  redis 就是一个数据库， 不过与传统数据库不同的是 redis 的数据是存在内存中的， 所以读写速度非常快，因此 redis 被广泛应用于缓存方向。另外， redis 也经常用来做分布式锁。 redis提供多种数据类型来支持不同的业务场景。除此之外，redis 支持事务，持久话，LUA脚本，LRU驱动事件，多种集群方案。

### 为什么要用redis/为什么要用缓存
主要从“高性能”和“高并发”这两点来看待这个问题。

**高性能：**
假如用户第一次访问数据库中的某些数据。这个过程会比较慢，因为是从硬盘上读取的，将该用户访问的数据存在缓存中，这样下一次再访问这些数据的时候就可以直接从缓存中获取了。操作缓存就是直接操作内存，所以速度相当快。如果数据库中的对应数据改变的之后，同步改变缓存中相应的数据即可！

**高并发：**
直接操作缓存能够承受的请求是远远大于直接访问数据库的，所以我们可以考虑把数据库中的部分数据转移到缓存中去，这样用户的一部分请求会直接到缓存这里而不用经过数据库。

### 为什么要用redis 而不用map/guava 做缓存？
缓存分为本地缓存和分布式缓存。以 Java 为例，使用自带的map或者guava实现的是本地缓存，最主要的特点就是轻量以及快速，生命周期随着 jvm 的销毁而结束，并且在多个实例的情况下，每个实例都需要各自保存一份缓存，缓存不具有一致性。

使用 redis 或者 memcached 之类的称为分布式缓存，在多实例的情况下，各实例公用一份缓存数据，缓存具有一致性。缺点是需要保持 redis 或 memcached服务的高可用，整个程序架构上较为复杂。

### redis 的线程模型
redis 内部使用文件事件处理器`file event handler`,这个文件事件处理器是单线程的，所以 redis 才叫做单线程的模型。它采用 IO 多路复用机制同时监听多个 socket，根据 socket 上的事件来选择对应的事件处理器进行处理。

文件事件处理器的结构包含 4 个部分:

- 多个 socket
- IO 多路复用程序
- 文件事件分派器
- 事件处理器(连接应答处理器，命令请求处理器，命令回复处理器)

多个 socket 可能会并发产生不同的操作，每个操作对应不同的文件事件，但是 IO 多路复用程序会监听多个 socket, 会将 socket 产生的事件放入队列中排队，事件分派器每次从队列中取出一个事件，把该事件交给对应的事件处理器进行处理。


### mac 连接远程redis 
    redis-cli -h 127.0.0.1
    创建值: set key value[...]
    获得值: get key
    查询有效时间: ttl key

### redis官网翻译博客，讲解红锁
    https://www.cnblogs.com/rgcLOVEyaya/p/RGC_LOVE_YAYA_1003days.html
    
### redisson 官网
    https://github.com/redisson/redisson/wiki/%E7%9B%AE%E5%BD%95



