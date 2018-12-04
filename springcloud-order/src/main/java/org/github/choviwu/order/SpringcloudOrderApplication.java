package org.github..order;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.github..api.auth.AuthService;
import org.github..common.tool.SpringBeanUtils;
import org.github..order.feign.AuthServiceConsumer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;

@EnableFeignClients
@MapperScan(basePackages = "org.github..order.mapper")
@EnableEurekaClient
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableTransactionManagement
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class},scanBasePackages = "org.github..order")
@RestController
public class SpringcloudOrderApplication {

	@RequestMapping(value = "/test")
	public void test() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		AuthServiceConsumer consumer = SpringBeanUtils.getInstance().getBean(AuthServiceConsumer.class);
		Object [] args = new Object[2];
		String userName = "";
		String password = "123123";
		args[0] = userName;
		args[1] = password;
		consumer.userLogin(userName,password);
//		MethodUtils.invokeMethod(consumer, "userLogin", args, args.getClass());
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext cpp = SpringApplication.run(SpringcloudOrderApplication.class, args);
		SpringBeanUtils.getInstance().setCfgContext(cpp);
	}
}
