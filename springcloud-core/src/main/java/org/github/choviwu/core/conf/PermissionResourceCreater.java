package org.github..core.conf;

import lombok.extern.slf4j.Slf4j;
import org.github..common.annotation.Serializer;
import org.github..common.util.AopUtils;
import org.github..common.util.SerializeUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

@Slf4j
public class PermissionResourceCreater implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("Init ====" + beanName + "====");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //Serializer
        Class clazz = AopUtils.getTargetClass(bean);
        if (clazz.isAnnotationPresent(Serializer.class)) {
            try {
                Object obj = clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return bean;
    }



    //对bean以及注解进行本地配置
//        Object target = AopUtils.getTargetObject(bean);
//        if(target==null){
//            target = bean;
//        }
//
//        if(!beanName.contains("Controller") && !beanName.contains("ServiceImpl")){
//            return bean;
//        }
//        log.info(" ========================="+beanName);
//        Class clazz = target.getClass();
//        Method[] methods = clazz.getMethods();
//        List<UserPermission> list = new LinkedList<>();
//        for(Method method : methods){
//            if(method.isAnnotationPresent(Permission.class)){
//                Permission permission = method.getAnnotation(Permission.class);

    //get className
//                String clazzName = clazz.getSimpleName();
////                clazzName.substring(0,clazzName.indexOf("ServiceImpl"))+
//                String permissionName = method.getName();//权限名
//                UserPermission userPermission = new UserPermission();
//                userPermission.setPermissionName(permissionName);
//                userPermission.setIsMenu(true);
//                userPermission.setCode(UUID.randomUUID().toString().replace("-",""));
//                list.add(userPermission);
//            }
//        }
    //resource register container
//        resourceFactory.createResource(list);


}
