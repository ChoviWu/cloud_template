package org.github..api.conf;

import org.github..api.web.SimpleFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author
 * @date 2018/7/27
 * Description :
 */
@Configuration
public class FilterConfig {


    //自定义的过滤器
    @Bean
    public Filter simpleFilter(){
        return new SimpleFilter();
    }
    @Bean
    public FilterRegistrationBean filterRegistrationBean2() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(simpleFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(7);
        return filterRegistrationBean;
    }
}
