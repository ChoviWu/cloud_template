package org.github..core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.github..common.util.JsonUtils;
import org.github..core.annotation.SignMatcher;
import org.github..redis.RedisRepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author
 * @date 2018/8/28
 * Description : sign 指定权限校验
 */
@Aspect
@Component
@Order(1)
public class UserSignAdvise {

    @Autowired
    private RedisRepositoryUser redisRepository;
//    @Autowired
//    private AuthServiceConsumer authServiceConsumer;

    @Pointcut(value = "@annotation(org.github..core.annotation.SignMatcher)")
    public void poinCut(){}

    @Around(value = "poinCut()&&@annotation(signMatcher)&&target(e)")
    public Object around(ProceedingJoinPoint pjp, SignMatcher signMatcher,Object e) throws Throwable {

        return pjp.proceed(pjp.getArgs());
    }

    @Before(value = "poinCut()")
    public void before(JoinPoint joinPoint){
        System.out.println(JsonUtils.toJson(joinPoint.getArgs()));

    }
    @AfterThrowing(pointcut = "poinCut()",throwing="e")
    public void afterThrowing(JoinPoint joinPoint,Throwable e) throws Throwable {
        try {
//            authServiceConsumer.

        } catch (Exception ex){
            throw e;
        }
    }
    @After(value = "poinCut()" )
    public void after(JoinPoint joinPoint ){
    }
    @AfterReturning(value = "poinCut()",returning = "token",argNames = "token")
    public void after2( Object token){
        System.out.println("第一个后置返回通知的返回值："+token);

    }
}
