package org.github..common.thread;

import javax.servlet.http.HttpServletRequest;

/**
 * @author
 * @date 2018/7/17
 * Description : 用户请求线程组
 */
public class RequestHolder {

    private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<>();

    public static void setRequestLocal(HttpServletRequest request) {
        requestLocal.set(request);
    }

    public static HttpServletRequest getRequestLocal() {
        return requestLocal.get();
    }

    public static void remove(){
        requestLocal.remove();
    }
}
