package com.cas.components.rwdb;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 15:10 2020-07-07
 * @version: V1.0
 * @review:
 */
public class DynamicDataSourceHolder {

    // 使用ThreadLocal记录当前线程的数据源key
    private static final ThreadLocal<DynamicDataSourceGlobal> holder = new ThreadLocal<>();

    /**
     * 记录数据源
     * @param dataSourceGlobal
     */
    public static void putDataSource(DynamicDataSourceGlobal dataSourceGlobal) {
        holder.set(dataSourceGlobal);
    }

    /**
     * 获取数据源
     * @return
     */
    public static DynamicDataSourceGlobal getDataSource() {
        return holder.get();
    }

    /**
     * 删除数据源
     */
    public static void clearDataSource() {
        holder.remove();
    }

}
