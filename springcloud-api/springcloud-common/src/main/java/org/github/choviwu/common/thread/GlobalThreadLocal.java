package org.github..common.thread;

import org.github..common.base.GlobalConfig;
import org.github..common.model.SysConfig;

import java.util.List;

/**
 * 全局线程共享配置
 */
public class GlobalThreadLocal {

    private ThreadLocal<List<SysConfig>> globalThread = new ThreadLocal();

    public void setGlobal(List<SysConfig> list){
        for (SysConfig config : list){
             GlobalConfig.Global.put(config.getParam(),config.getValue());
        }
        globalThread.set(list);
    }

    public String getGlobal(String param){
        return GlobalConfig.Global.get(param);
    }
    public void removeGlobal(String param){
        param = param!=null ? null : GlobalConfig.Global.remove(param);
    }
    public void removeGlobal(){
        removeGlobal(null);
    }
}
