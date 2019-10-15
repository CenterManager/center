package com.jyjx.yxdl.common;

public class DataSourceType {

//    public enum DataBaseType {
//        MASTER, SLAVE
//    }

    // 使用ThreadLocal保证线程安全
    private static final ThreadLocal<String> TYPE = new ThreadLocal<>();

    // 往当前线程里设置数据源类型
    public static void setDataBaseType(String dataBaseType) {
        if (dataBaseType == null) {
            throw new NullPointerException();
        }
        System.err.println("[将当前数据源改为]：" + dataBaseType);
        TYPE.set(dataBaseType);
    }

    // 获取数据源类型
    public static String getDataBaseType() {
        String dataBaseType = TYPE.get() == null ? DataBaseType.MASTER : TYPE.get();
        System.err.println("[获取当前数据源的类型为]：" + dataBaseType);
        return dataBaseType;
    }

    // 清空数据类型
    public static void clearDataBaseType() {
        TYPE.remove();
    }

}