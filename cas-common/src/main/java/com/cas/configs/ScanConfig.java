package com.cas.configs;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 17:30 2020-02-11
 * @version: V1.0
 * @review: 这个类可以遍历所有的类，并打印出来 不可以跨模块，因为这个是通过class来寻找类
 */
@Service
public class ScanConfig {

    private static List<String> classPaths = new ArrayList<>();

    public void get() throws ClassNotFoundException{
        //包名/Users/xianglong/IdeaProjects/cas/cas-web/out/strTest/classes/
        String basePack = "com.cas";
        //先把包名转换成路径，首先得到项目的classpath
        String classpath = ScanConfig.class.getResource("/").getPath();
        //然后吧我们的包名basePath转换为路径名
        basePack = basePack.replace(".", File.separator);
        //然后把classpath 和 basePack 合并
//        String searchPath = classpath + basePack;
        String searchPath = "/Users/xianglong/IdeaProjects/cas";
        doPath(new File(searchPath));
        //这个时候我们已经得到了制定包下所有的类的绝对路径了。我们现在利用这些绝对路径和java的反射机制得到他们的类对象
        for(String s : classPaths) {
            //把 D:\work\code\20170401\search-class\target\classes\com\baibin\search\a\A.class 这样的绝对路径转换为全类名com.baibin.search.a.A
//            s = s.replace(classpath.replace("/", "\\").replaceFirst("\\\\", ""), "").replace("\\", ".").replace(".class","");
//            Class cls = Class.forName(s);
//            System.out.println(cls);
            System.out.println(s);
            //我们可以从这里解析所有我们想要处理的文件，解析+正则处理




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
            if(file.getName().endsWith("Mapper.xml")) {
                //如果是class文件我们放入我们的集合中。
                classPaths.add(file.getPath());
            }
        }
    }

}
