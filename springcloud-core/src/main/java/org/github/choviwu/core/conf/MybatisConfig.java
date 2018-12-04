package org.github..core.conf;

import com.github.pagehelper.autoconfigure.MapperProperties;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import tk.mybatis.mapper.common.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @date 2018/8/29
 * Description :
 */
@org.springframework.context.annotation.Configuration
@MapperScan(basePackages = "org.github..*.mapper")
//@ConditionalOnProperty(prefix = "mybatis.configuration",name = "aggressiveLazyLoading")
public class MybatisConfig {


//    @Bean
//    @Primary
//    public MybatisProperties mybatisProperties(){
//        MybatisProperties mybatisProperties =  new MybatisProperties();
//        mybatisProperties.setMapperLocations(new String []{"classpath*:mapper/*.xml"});
//        mybatisProperties.setTypeAliasesPackage("org.github..common.model");
//        mybatisProperties.setTypeHandlersPackage("org.github..*.mapper");
//        Configuration configuration = new Configuration();
//        configuration.setUseGeneratedKeys(true);//生成主键
//        configuration.setAggressiveLazyLoading(true);//开启懒加载
//        configuration.setCacheEnabled(true);//开启缓存
//        configuration.setMapUnderscoreToCamelCase(true);//驼峰
//        mybatisProperties.setConfiguration(configuration);
//        return mybatisProperties;
//    }

//    @Bean
//    @Primary
//    public MapperProperties tkMapper(){
//        MapperProperties mapperProperties = new MapperProperties();
//        List<Class> mappers = new ArrayList();
//        mappers.add(Mapper.class);
//        mapperProperties.setMappers(mappers);
//        mapperProperties.setIdentity("MYSQL");
//        mapperProperties.setNotEmpty(false);
//        return mapperProperties;
//    }

}
