package org.github..core.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.github..common.util.JsonUtils;
import org.github..core.annotation.Permission;
import org.github..common.bean.ResourceFactory;
import org.github..common.bean.UserDetails;
import org.github..common.bean.UserPermission;
import org.github..common.constant.RedisConstants;
import org.github..common.exception.ExceptionMessage;
import org.github..common.exception.PermissionException;
import org.github..common.thread.RequestHolder;
import org.github..common.util.AssertUtil;
import org.github..common.util.StringUtils;
import org.github..redis.RedisRepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author
 * @date 2018/7/11
 * Description : 权限切面
 */
@Component
@Aspect
@Order(2)
@Slf4j
public class PermissionAdvise {

    @Autowired
    RedisRepositoryUser redisRepository;
    @Autowired
    private ResourceFactory resourceFactory;
    private static HttpServletRequest request;

    @Pointcut(value = "@annotation(org.github..core.annotation.Permission)")
    public void pointCut() {
    }

    @Around(value = "pointCut()&&@annotation(permission)")
    public Object around(ProceedingJoinPoint pjp, Permission permission) throws Throwable {
        request = getRequest();
        System.out.println("==========================" + request.hashCode());
        List<UserPermission> list = resourceFactory.getList();
        Object args[] = pjp.getArgs();
        Class clazz = permission.clazz();
        Object filter = clazz.newInstance();
        UserDetails userDetails =  this.checkUserStatus();
        userDetails.setUserPermissions(list);
        userDetails.setRequest(request);
        //
        Method method = clazz.getMethod("matchPermission", Permission.class, UserDetails.class);
        Object obj = method.invoke(filter, permission, userDetails);
        Object result = pjp.proceed(args);
        if(result!=null){
            System.out.println(JsonUtils.toJson(result));
        }
        return result;
    }

    private UserDetails checkUserStatus() {
        //check user token
        String token = request.getHeader("x-access-token");
        String obj = redisRepository.get(RedisConstants.USER_TOKEN + token);
        AssertUtil.isNullOrEmpty(obj,ExceptionMessage.Permission.PLEASE_LOGIN_FIRST);
        UserDetails details = JsonUtils.json2Object(obj,UserDetails.class);
        //check user login status
        if (StringUtils.isEmpty(details)) {
            throw new PermissionException(ExceptionMessage.Permission.PLEASE_LOGIN_FIRST);
        }
        return details;
    }

    public HttpServletRequest getRequest() {
        return RequestHolder.getRequestLocal();
    }
}
