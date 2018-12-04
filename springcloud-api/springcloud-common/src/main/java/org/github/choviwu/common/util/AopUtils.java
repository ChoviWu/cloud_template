package org.github..common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.SpringProxy;
import org.springframework.aop.TargetClassAware;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 * @author
 * @date 2018/7/10
 * Description :
 */
@Slf4j
public class AopUtils {

    public static Object getTargetObject(Object cadidate)  {
        //如果不是代理 直接返回
        if(!isAopProxy(cadidate)){
            return cadidate;
        }
        try {
            if (isCglibProxy(cadidate)) {
                return getCglibProxyTargetObject(cadidate);
            }else  {
                return getJdkDynamicProxyTargetObject(cadidate);
            }
        }catch (Exception e){
            log.info(" bean is Not proxy object" + e.getMessage());
            return null;
        }
    }

    public static Class<?> getTargetClass(Object candidate) {
        Assert.notNull(candidate, "Candidate object must not be null");
        Class<?> result = null;
        if (candidate instanceof TargetClassAware) {
            result = ((TargetClassAware)candidate).getTargetClass();
        }

        if (result == null) {
            result = isCglibProxy(candidate) ? candidate.getClass().getSuperclass() : candidate.getClass();
        }

        return result;
    }
    public static boolean isCglibProxy(@Nullable Object object) {
        return object instanceof SpringProxy && ClassUtils.isCglibProxy(object);
    }

    public static boolean isAopProxy(@Nullable Object object) {
        return object instanceof SpringProxy && (Proxy.isProxyClass(object.getClass()) || ClassUtils.isCglibProxyClass(object.getClass()));
    }

    public static boolean isJdkDynamicProxy(@Nullable Object object) {
        return object instanceof SpringProxy && Proxy.isProxyClass(object.getClass());
    }
    private static Object getCglibProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        h.setAccessible(true);
        Object dynamicAdvisedInterceptor = h.get(proxy);
        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        return ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
    }

    private static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        AopProxy aopProxy = (AopProxy) h.get(proxy);
        Field advised = aopProxy.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        return ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();
    }
}
