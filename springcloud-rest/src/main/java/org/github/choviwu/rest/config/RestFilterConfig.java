package org.github..rest.config;

import org.github..rest.web.AccessFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author
 * @date 2018/7/30
 * Description :
 */
@Configuration
public class RestFilterConfig {


    //自定义的过滤器
    @Bean
    public Filter accessFilter(){
        return new AccessFilter();
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean1(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(accessFilter());
        filterRegistrationBean.addUrlPatterns("/auth", "/user/*", "/sys/*");
        //order的数值越小 则优先级越高
        filterRegistrationBean.setOrder(6);
        return filterRegistrationBean;
    }
}
