package org.github..common.test;

import org.github..common.bean.DefineResourceFactory;
import org.github..common.bean.ResourceFactory;
import org.github..common.model.BasUser;
import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author
 * @date 2018/7/10
 * Description :
 */
public class MyTest {

    @Test
    public void test() {
        BasUser user = new BasUser();
        user.setPassword("123");
        Class<BasUser> clazz = (Class<BasUser>) user.getClass();
        try {
            Method method = clazz.getMethod("setPassword",String.class);

            Object controller1 =  method.invoke(user,"abc");
            System.out.println(controller1);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long time1 = System.currentTimeMillis();
        Thread.sleep(1000);
        long time = System.currentTimeMillis() - time1;
        System.out.println(time);
        // 获取Java线程管理MXBean
//        ThreadMXBean threadMXBean =  ManagementFactory.getThreadMXBean();
//        // 获取线程和线程堆栈信息
//        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);
//        // 遍历线程线程，仅打印线程ID和线程名称信息
//        for(ThreadInfo threadInfo:threadInfos){
//            System.out.println("["+threadInfo.getThreadId()+"]"+threadInfo.getThreadName());
//        }
    }

    @Test
    public void test1(){
        List list = new ArrayList<>();
        list.add("abc");
        list.add("");
        list.add(null);
        String []str = new String []{"abc",""};
        Arrays.stream(str).forEach(s -> System.out.println(s));
        list.stream().forEach(s -> System.out.println(s));
        list.stream().filter(Objects :: nonNull).forEach(s -> System.out.print(s+ "  "));
        System.out.println("Match^^^^ "+list.stream().allMatch(Objects :: nonNull));
        System.out.println("aaa^^^^ "+list.stream().filter(s -> Objects.equals(str[0],str[1])));
        System.out.println("===" + Arrays.stream(str).filter(s -> Objects.equals(s,"abc")).findFirst().get());
    }



    @Test
    public void test2(){
        ServiceLoader<ResourceFactory > load = ServiceLoader.load(ResourceFactory .class);
       for (ResourceFactory factory : load){
           System.out.println(factory.getList());
       }
    }
}
