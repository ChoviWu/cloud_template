package org.github..mq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author
 * @date 2018/8/13
 * Description :
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableRabbit
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix //断路器支持
@MapperScan(basePackages = "org.github..mq.mapper")
@ComponentScan(basePackages = {"org.github."})
public class RabbitMqApplication {


    public static void main(String[] args) {
        SpringApplication.run(RabbitMqApplication.class, args);
    }

}
