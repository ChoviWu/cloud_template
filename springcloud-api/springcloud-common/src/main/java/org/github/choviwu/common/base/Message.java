package org.github..common.base;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author
 * @date 2018/6/20
 * Description :
 */
public final class Message {

    public static Map<String,String> OK = new ConcurrentHashMap<>();
    public static Map<String,String> FAIL = new ConcurrentHashMap<>();
    public static Map<String,String> RESULT = new ConcurrentHashMap<>();
    static {
        OK.put("result","success");
        FAIL.put("result","fail");
    }
}
