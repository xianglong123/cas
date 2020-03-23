# 运行参数
    -XX:FieldsAllocationStyle 分配策略参数，影响虚拟机存储父类继承的数据和子类定义的字段的存储顺序
    +XX:CompactFields 设置为True 那么子类之中较窄的变量允许插入父类变量的空隙之中，以节省一点点空间(默认为True)
    -Xms20m 堆最小值20m
    -Xmx20m 堆最大值20m (堆最大值和最小值相等可避免堆自动扩展)
    -XX:+HeapDumpOnOutOfMemoryError  可以让虚拟机出现内存溢出异常的时候Dump出当前的内存堆快照，以便后期分析
    -XX:HeapDumpPath=/Users/xianglong/Desktop/其他/Dump 堆快照转储保存路径