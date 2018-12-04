package org.github..core.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.github..common.annotation.CutUtil;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author
 * @date 2018/7/9
 * Description : AOP Aspect Class
 */
@Aspect
@Component
@Slf4j
@Order(666)
public class PointCutAspect {

    @Pointcut(value = "@annotation(org.github..common.annotation.CutUtil)")
    public void pointCut() {
    }

    /**
     * Param Location have To commom ！！！
     *
     * @param pjp
     * @param cutUtil
     * @return
     * @throws Throwable
     */
    @Around(value = "pointCut()&&@annotation(cutUtil)")
    public Object around(ProceedingJoinPoint pjp, CutUtil cutUtil) throws Throwable {
        log.info(cutUtil.value());
        return pjp.proceed(pjp.getArgs());
        //throw new RuntimeException("hewww");
    }


}
