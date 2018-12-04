package org.github..rest;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.github..core.conf.FeignConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class, MongoAutoConfiguration.class, FeignConfiguration.class,
		MongoDataAutoConfiguration.class})
@EnableFeignClients
@EnableDiscoveryClient
@EnableHystrix
@ComponentScan(basePackages = {"org.github."})
public class SpringcloudRestApplication {


	public static void main(String[] args) {

		SpringApplication.run(SpringcloudRestApplication.class, args);
	}
}
