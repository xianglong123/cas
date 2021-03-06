# 1. 运行时数据区域
    运行时数据区域包括 程序计数器 JAVA虚拟机栈 本地方法栈 方法区 堆
    线程私有：程序计数器 JAVA虚拟机栈 本地方法栈
    线程非私有：方法区 堆

## 1.1 程序计数器 
    程序计数器是当前线程所执行的字节码的行号指示器，他是程序控制流的指示器，分支，循环，跳转，异常处理，线程恢复都
    依赖它。这个计数器记录的是正在执行的虚拟机字节码指令的地址；如果他正在执行一个Native方法，这个计数器值应为空，
    他是唯一一个不会内存溢出的组健 
    
## 1.2 JAVA 虚拟机栈(对象类型数据)
    JAVA虚拟机栈在每个方法执行的时候都会创建一个栈帧，栈帧存储局部变量表，操作数栈，动态连接，方法出口等信息。
    每次方法的执行过程都对应着栈帧从虚拟机中入栈到出栈的过程，栈帧的生命周期和线程类似

**局部变量表存放了 编译期可知的8中基本数据类型(boolean, byte, char, short, int, float, long, double), 对象引用**
--数据类型在局部变量表中的储存空间以局部变量槽来表示，long和double类型占两个变量槽，其他类型占一个，在方法运行之前他所需的内存空间在编译器
就已经确定，这个由虚拟机自己决定怎么分配。当线程所请求的深度大于虚拟机所允许的深度就会报：StackOverflowError 动态扩展时如果无法获取足够的
内存空间就会报OOM

## 1.3 本地方法栈
    本地方法栈和虚拟机栈类似，只是本地方法栈为本地方法服务Native，虚拟机栈为java程序服务，Native方法底层实现不限语言，在请求栈深度大于虚拟机
    允许深度和申请不到足够的内存空间也会报：StackOverflowError和OOM

## 1.4 Java 堆 (运行时常量池，静态变量，对象实例数据)
    java堆 在虚拟机启动时创建，是唯一一个存放对象的区域，对象所占内存由虚拟机分配，java堆是GC(对象回收)的主要区域，分为老年区，新生代，永久代
    幸存区。大致流程就是幸存区GC后的对象到新生代，新生代GC后存活的对象到老年代，老年代存活的对象到永久代
    
## 1.5 方法区(规范，无实体)
    方法区存放 被加载的类型信息，常量，静态常量

### 1.5.1 运行时常量池 (堆)

    运行时常量池是方法区的一部分，存放编译期生成的各种字面量与符号引用，这部分内容将在类加载后存放到方法区的运行时常量池中。
    在jdk6之前运行时常量池还是永久代的一部分，但是jdk7之后运行时常量池就变成了java堆的一部分了，可以通过限制堆大小限制运行时常量池的大小

### 1.5.2   元空间
    元空间是取代以前永久代的产物，储存类的元数据   
    什么是元数据?
    　　元数据是指用来描述数据的数据，更通俗一点，就是描述代码间关系，或者代码与其他资源（例如数据库表）之间内在联系的数据。
    在一些技术框架，如struts、EJB、hibernate就不知不觉用到了元数据。对struts来说，元数据指的是struts-config.xml;对EJB来说，
    就是ejb-jar.xml和厂商自定义的xml文件；对hibernate来说就是hbm文件。以上阐述的几种元数据都是基于xml文件的或者其他形式的单独配置文件。
    这样表示有些不便之处。一、与被描述的文件分离，不利于一致性的维护；第二、所有这样文件都是ASCII文件，没有显式的类型支持。
    基于元数据的广泛应用，JDK5.0引入了Annotation的概念来描述元数据。在java中，元数据以标签的形式存在于java代码中，元数据标签的存在并不影响程序代码的编译和执行。
 
# 2. 对象的创建
    首先我们通过new创建对象，我们知道运行时常量池中保存了对符号的引用，当虚拟机检测到new关键字被引用，那么就能找到要初始化的对象并判断是否被
    加载，解析，初始化过。如果没有就会被执行对象构造的一个过程，首先我们要知道一个对象是需要内存的，所以怎么分配内存呢？
---
`怎么分配内存？？？`

    分配内存有两种方式，
    第一种是"指针对撞"，如果虚拟机的内存空间是规整的，我们只需要将内存指针向下拨动对象大小即可完成内存分配
    第二种是"空闲列表"，如果虚拟机的内存空间是碎裂的，我们有第三张表去记录哪些地方是空闲的，通过这张表实现内存的分配
    到底采用哪种方式呢？这个由虚拟机内存是否规整决定，而虚拟机内存是否规整由GC回收器决定，如果采用的是Serial,ParNew这种带有
    压缩整理过程的收集器，我们就采用"指针碰撞"的方式，但是如果我们采用的是CMS这种基于清除算法的收集器，就只能采用"空闲列表"的方式。
    到此，我们还有一个问题，给一个对象分配内存是没有任何问题的，但是在高并发的情况下，如果两个对象同时分配了一块内存，肯定是会有冲突的，
    那么我们怎么解决内存分配冲突呢？
---
`怎么解决内存分配冲突？`

    解决内存分配冲突有两种方式，
    第一种就是对象分配内存进行同步处理(我觉得是一种性能换安全的做法)，现实是通过CAS配上失败重试来做到对象分配的原子性，
    第二种就是采用缓冲的方式，在线程创建的时候我们就给线程分配内存，在其构建对象的时候，不直接使用java堆内存，而是采用本地线程分配缓冲区，
    这样只有本地线程缓冲区被用完，再申请新的内存时候才会需要做同步处理(这是一种降低同步处理次数的机制)
**本地线程分配缓冲区(Thread Local Allocation Buffer)**

---
`接下来，需要给对象的进行必要的设置`
    
    设置诸如这个对象是哪个类的实例，如果才能找到类的元数据信息，对象的哈希码，对象的GC分代年龄等信息，是否启用偏向锁，
    到这里对象对于虚拟机来说已经被创建了，但是对于java程序还没有，还需要按照程序员的意愿对其分配值
    
    什么是偏向锁？？？
---
`最后对象执行<init>方法，对象就被创建了`
   
## 对象的内存分布
    对象在堆内存的分布一共分为三个部分：对象头，实例数据，对齐填充
    
    对象头两类信息：
    第一类是用于存储对象自身运行时数据：哈希码，GC分代年龄，锁状态标志，线程持有的锁，偏向线程ID，偏向时间戳
    第二类是类型指针：即对象指向它的类型元数据的指针，java虚拟机通过这个指针来确定对象是哪个类的实例
    
    实例数据：我们在代码中定义的数据，父类的数据和子类的数据都需要被储存
    
    对齐填充：HotSpot虚拟机的自动内存管理系统要求对象的初始地址必须是8的倍数，所以对齐补充就是做个占位符，方便下一个对象分配
## 对象的访问定位
    对象有了，我们怎么使用，我们知道对象的使用在方法中，方法使用对象的时候会去java栈找对象的引用，通过reference寻找对象
    一共有两种方式：
    第一种句柄访问，也就是reference中存储句柄的地址，句柄中存储对象的实例数据和对象的类型数据，
    第二种直接指针访问，就是reference直接指向对象的实例数据，实例数据中多一个内存储存对象的类型数据。
    
    对象的实例数据存储在java堆中，java的类型数据存储在java栈中。。。。
    
    句柄访问多一次寻址的开销，但是也有优点，改变对象的地址，只需要改变句柄的引用即可
    

    