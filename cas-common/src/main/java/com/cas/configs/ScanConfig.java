package com.cas.configs;

import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 17:30 2020-02-11
 * @version: V1.0
 * @review: 这个类可以遍历所有的类，并打印出来 不可以跨模块，因为这个是通过class来寻找类
 */
@Service
public class ScanConfig {

    // 保存检索的所有类
    private static List<String> classPaths = new ArrayList<>();
    // 待处理数据
    private static List<Class> waitExec = new ArrayList<>();
    // 有问题的类
    private static List<Class> errorList = new ArrayList<>();

    public void get2() throws ClassNotFoundException{
        //包名/Users/xianglong/IdeaProjects/cas/cas-web/out/strTest/classes/
        String basePack = "com.cas";
        //先把包名转换成路径，首先得到项目的classpath
//        String classpath = ScanConfig.class.getResource("/").getPath();
        String classpath = "/Users/xianglong/IdeaProjects/cas/cas-dao/src/main/java/";
        //然后吧我们的包名basePath转换为路径名
        basePack = basePack.replace(".", File.separator);
        //然后把classpath 和 basePack 合并
        String searchPath = classpath + basePack;
//        String searchPath = "/Users/xianglong/IdeaProjects/cas";
        doPath(new File(searchPath));
        //这个时候我们已经得到了制定包下所有的类的绝对路径了。我们现在利用这些绝对路径和java的反射机制得到他们的类对象
        for(String s : classPaths) {
            //把 D:\work\code\20170401\search-class\target\classes\com\baibin\search\a\A.class 这样的绝对路径转换为全类名com.baibin.search.a.A
//            s = s.replace(classpath.replace("/", "\\").replaceFirst("\\\\", ""), "").replace("\\", ".").replace(".class","");
            s = s.replace(classpath, "").replace("/", ".").replace(".java", "");
            Class cls = Class.forName(s);
            getViolationsSign(cls);
            doExec();
            // 处理违规单例模式
//            System.out.println(cls);
//            System.out.println(s);
            //我们可以从这里解析所有我们想要处理的文件，解析+正则处理
        }
    }

    public void get() throws ClassNotFoundException{
        // 搞一个多线程
        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        //先把包名转换成路径，首先得到项目的classpath
        String[] classpath = {"/Users/xianglong/IdeaProjects/cas/cas-dao/src/", "/Users/xianglong/IdeaProjects/cas/cas-web/src"};
        // 获取所有的类,多线程执行
//        Arrays.asList(classpath).forEach((serachPath) -> poolExecutor.execute(() -> doPath(new File(serachPath))));
        Arrays.asList(classpath).forEach((serachPath) -> doPath(new File(serachPath)));
        //这个时候我们已经得到了制定包下所有的类的绝对路径了。我们现在利用这些绝对路径和java的反射机制得到他们的类对象
        for(String s : classPaths) {
            s = s.replace(".java", "").replace("/", ".").replaceAll(".*java\\.", "");
            Class cls = Class.forName(s);
            // 第一次筛选
            getViolationsSign(cls);
            // 最终筛选
            doExec();
        }
    }

    /**
     * 最终判断
     */
    public void doExec() {
        // 搞一个线程池
        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        for (Class clz : waitExec) {
            Method[] methods = clz.getMethods();
            for (Method method : methods) {
                // 要求：无参数，返回值类型为本类, 这就初步认定为获得单例的方法，违规规则为方法不具有线程安全性
                if (method.getReturnType() == clz && method.getParameterCount() == 0) {
                    // 这个是获得单例方法的违规判断，怎么判断线程不安全
                    // ......(没想出来)
                    // 开10个线程跑，若所取对象的内存地址一致，则默认方法安全[效果不好]
                    // 第二种，判断是否有同步关键字和锁相关类

                    // 方法上有同步关键字，不好，应该加载核心判空方法上面
                    if (method.toGenericString().contains("synchronized")) {
                        errorList.add(clz);
                    } else if (true){
                        // ？？？？


                    }
                }
            }
        }
    }

    /**
     * 第二次筛选出可能是单例的类，规则是这个类中有本类的属性，则初步判定是单例模式
     * @param cls
     */
    public  void getViolationsSign(Class cls) {
        Field[] declaredFields = cls.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getType() == cls) {
                // 判断是不是静态的，静态的就获取值
                boolean isStatic = Modifier.isStatic(field.getModifiers());
                //
                if (isStatic) {
                    field.setAccessible(true);
                    try {
                        Object o = field.get(cls);
                        if (o == null) {
                            waitExec.add(cls);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                // 如果有，先初步判定为单例模式，之后再三次违规筛选
                return ;
            }
        }
    }

    /**
     * 该方法会得到所有的类，将类的绝对路径写入到classPaths中222
     * 核心方法
     */
    private void doPath(File file) {
        if(file.isDirectory()) {//文件夹
            //文件夹我们就递归
            File[] files = file.listFiles();
            for(File f1 : files) {
                doPath(f1);
            }
        } else { //标准文件
            //标准文件我们就判断是否是class文件
            if(file.getName().endsWith(".java") && !file.getName().contains("Enum") && !file.getName().contains("Mapper")) {
                //如果是class文件我们放入我们的集合中。
                classPaths.add(file.getPath());
            }
        }
    }

}
