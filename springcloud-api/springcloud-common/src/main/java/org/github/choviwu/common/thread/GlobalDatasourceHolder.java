package org.github..common.thread;

import javax.sql.DataSource;

public final class GlobalDatasourceHolder {

    public static ThreadLocal<String> dataSourceThreadLocal = new ThreadLocal<>();

    public static void setDataSourceThreadLocal(String  dataSource){
        dataSourceThreadLocal.set(dataSource);
    }

    public static void remove(){
        dataSourceThreadLocal.remove();
    }

    public static String  get(){
        return dataSourceThreadLocal.get();
    }
}
