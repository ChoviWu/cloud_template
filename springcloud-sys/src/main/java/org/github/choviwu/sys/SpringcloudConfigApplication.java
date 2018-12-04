package org.github..sys;

import org.github..common.base.GlobalConfig;
import org.github..common.model.SysConfig;
import org.github..sys.mapper.SysConfigMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.data.solr.SolrRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

//服务注册
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = {"org.github..*.mapper"})
@ComponentScan(basePackages = {"org.github."})
public class SpringcloudConfigApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringcloudConfigApplication.class, args);
		SysConfigMapper configMapper = context.getBean(SysConfigMapper.class);
		List<SysConfig> list = configMapper.selectAll();
		//TODO Cast Error
		if(list!=null && list.size()>0){
			for (SysConfig config : list){
				GlobalConfig.Global.put(config.getParam(),config.getValue());
			}
		}
	}
}
