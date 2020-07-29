package com.cas.wykt.java高级开发.classLoader;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 09:12 2020-07-24
 * @version: V1.0
 * @review: 查看类加载器实例
 */
public class ClassLoaderView {

    public static void main(String[] args) throws ClassNotFoundException {

        // 加载核心类库的 bootStrap classLoader
        System.out.println("核心类库加载器："
        + ClassLoaderView.class.getClassLoader().loadClass("java.lang.String").getClassLoader());

        // 加载扩展库的 Extension ClassLoader
        System.out.println("扩展类库加载器："
        + ClassLoaderView.class.getClassLoader().loadClass("com.sun.security.auth.LdapPrincipal").getClassLoader());

        // 加载应用程序的
        System.out.println("应用程序库加载器："
        + ClassLoaderView.class.getClassLoader());

        // 双亲委派模型 Parents Delegation Model
        System.out.println("应用程序库加载器的父类：" + ClassLoaderView.class.getClassLoader().getParent());
        System.out.println("应用程序库加载器的父亲的父亲：" + ClassLoaderView.class.getClassLoader().getParent().getParent());

    }

}
