### jps 虚拟机进程状况工具
    jps [options] [hostid]
    options:
        -q : 只输出LVMID，省略主类的名称
        -m : 输出虚拟机进程启动时传递给主类main()函数的参数
        -l : 输出主类的全名，如果进程执行的JAR包，则输出jar路径
        -v : 输出虚拟机进程启动时的JVM参数
        
### jstat 虚拟机统计信息监视工具
    jstat [option vmid [interval[s|ms] [count]]]
    options:
        -class : 监视类加载，卸载数量，总空间以及类装载所消耗的时间
        -gc : 监视java堆状况，包括Eden区，2个Survivor区，老年代，永久代的容量，已用空间，垃圾收集器时间合计等信息
        -gccapacity : 监视内容与-gc基本相同，但输出主要关注Java堆各个区域使用到的最大，最小空间
        -gcutil : 监视内容与-gc基本相同，但输出主要关注已使用空间占总空间的百分比
        -gcnew : 监视新生代垃收集状况
        -gcnewcapacity : 监视内容与-gcnew基本相同,输出主要关注使用到的最大，最小空间
        -gcold : 监视老年代的垃圾收集状态
        -gcoldcapacity : 监视内容与-gcold基本相同，输出主要关注使用到的最大，最小空间
        -gcpermcapacity : 输出永久代使用到的最大，最小空间
        -compiler : 输出即时编译器编译过的方法，耗时等信息
        -printcompilation : 输出已经被即时编译的方法
        
        S0：幸存1区当前使用比例
        S1：幸存2区当前使用比例
        E：伊甸园区使用比例
        O：老年代使用比例
        M：元数据区使用比例
        CCS：压缩使用比例
        YGC：年轻代垃圾回收次数
        FGC：老年代垃圾回收次数
        FGCT：老年代垃圾回收消耗时间
        GCT：垃圾回收消耗总时间
        
### jinfo 配置信息工具
### jmap Java内存映像工具
### jhat 虚拟机堆转储快照分析工具
### jstack Java堆栈跟踪工具

