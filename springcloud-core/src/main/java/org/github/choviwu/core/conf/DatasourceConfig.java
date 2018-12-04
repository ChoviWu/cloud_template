package org.github..core.conf;

import com.alibaba.druid.pool.DruidDataSource;
import org.github..core.conf.properties.DruidProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author
 * @date 2018/8/29
 * Description : ConditionalOnProperty读取application.properties  name属性  如果为空 则不进行配置
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.datasource.druid",name = "url")
public class DatasourceConfig {

    @Autowired
    DruidProperties druidProperties;


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DruidProperties druidProperties(){
        return new DruidProperties();
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
    /**
     * druid数据库连接池
     */
    @Bean(initMethod = "init")
    public DruidDataSource dataSource() {
        MyDruidDataSource dataSource = new MyDruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }
}
