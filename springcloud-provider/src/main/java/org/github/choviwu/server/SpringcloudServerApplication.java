package org.github..server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;

/**
 * Server Register Center
 * created by
 */
@EnableEurekaServer
@ComponentScan(basePackages = {"org.github."})
/**
 * exclude this not used clazz
 */
@SpringBootApplication
@Slf4j
public class SpringcloudServerApplication{

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudServerApplication.class, args);
	}

}
