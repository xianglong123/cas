# 运行参数
    -XX:FieldsAllocationStyle 分配策略参数，影响虚拟机存储父类继承的数据和子类定义的字段的存储顺序
    +XX:CompactFields 设置为True 那么子类之中较窄的变量允许插入父类变量的空隙之中，以节省一点点空间(默认为True)
    -Xms20m 堆最小值20m
    -Xmx20m 堆最大值20m (堆最大值和最小值相等可避免堆自动扩展)
    -XX:+HeapDumpOnOutOfMemoryError  可以让虚拟机出现内存溢出异常的时候Dump出当前的内存堆快照，以便后期分析
    -XX:HeapDumpPath=/Users/xianglong/Desktop/其他/Dump 堆快照转储保存路径
    -XX:+PrintGCDetails 打印GC日志
    -XX:SurvivorRatio=8 Enden与Survivor区的空间比例是8:1
    -XX:PretenureSizeThreshold 指定该设置值的对象直接进入老年代进行分配   避免大对象在from和to之间来回复制   不能写MB 要写具体的数值
    -XX:MaxTenuringThreshold  对象晋升老年代的年龄阔值
    -XX:HandlePromotionFailure=true (在jdk6之后此数值已经失效，采用默认开启) 空间分配担保  如果老年代空间剩余大于Survivor，那么就会Minor GC 反之进行Full GC 
    
    
    