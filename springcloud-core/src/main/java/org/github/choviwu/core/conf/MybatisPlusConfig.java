package org.github..core.conf;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.boot.starter.GlobalConfig;
import com.baomidou.mybatisplus.spring.boot.starter.MybatisPlusProperties;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author
 * @date 2018/8/30
 * Description :
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.datasource.druid",name = "url")
public class MybatisPlusConfig {

    /**
     * mybatis-plus 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        return page;
    }

    /**
     * mybatis-plus properties config
     * @return
     */
    @Bean
    @Primary

    public MybatisPlusProperties mybatisProperties(GlobalConfig mybatisGlobalConfig,
                                                   MybatisConfiguration mybatisplusConfiguration){
        MybatisPlusProperties mybatisProperties =  new MybatisPlusProperties();
        mybatisProperties.setMapperLocations(new String []{"classpath*:mapper/*.xml"});
        mybatisProperties.setTypeAliasesPackage("org.github..common.model");
        mybatisProperties.setTypeHandlersPackage("org.github..*.mapper");

        mybatisProperties.setConfiguration(mybatisplusConfiguration);


        // 自定义填充策略接口实现
        // meta-object-handler: com.baomidou.springboot.xxx
        mybatisProperties.setGlobalConfig(mybatisGlobalConfig);
        return mybatisProperties;
    }
    @Bean("mybatisGlobalConfig")
    public GlobalConfig mybatisGlobalConfig(){
        GlobalConfig config = new GlobalConfig();
        config.setDbColumnUnderline(true);
        config.setFieldStrategy(2);//字段策略 0:"忽略判断",1:"非 NULL 判断"),2:"非空判断"
        config.setIdType(2);//#主键类型  0:"数据库ID自增", 1:"用户输入ID",2:"全局唯一ID (数字类型唯一ID)", 3:"全局唯一ID UUID";
        config.setDbColumnUnderline(true);//驼峰下划线转换
        config.setRefreshMapper(true);//刷新mapper 调试神器
        config.setCapitalMode(true);//数据库大写下划线转换
        config.setLogicDeleteValue("0");
        config.setLogicNotDeleteValue("1");
        config.setSqlInjector("com.baomidou.mybatisplus.mapper.LogicSqlInjector");
        return config;
    }
    @Bean("mybatisplusConfiguration")
    public MybatisConfiguration mybatisplusConfiguration(){
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setUseGeneratedKeys(true);//生成主键
        configuration.setAggressiveLazyLoading(true);//开启懒加载
        configuration.setCacheEnabled(true);//开启缓存
        configuration.setMapUnderscoreToCamelCase(true);//驼峰
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setSafeResultHandlerEnabled(true);
        return configuration;
    }
    /***
     * plus 的性能优化
     * @return
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        /*<!-- SQL 执行性能分析，开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长 -->*/
        performanceInterceptor.setMaxTime(1000);
        /*<!--SQL是否格式化 默认false-->*/
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }
//    /**
//     * @Description : druid注入
//     */
//    @Bean
//    @ConfigurationProperties("spring.datasource.druid." )
//    public DataSource dataSource() {
//        return DruidDataSourceBuilder
//                .create()
//                .build();
//    }
}
