package org.github..rest.config;

import feign.Feign;
import feign.RequestInterceptor;
import org.github..core.feign.MyErrorDecoder;
import org.github..rest.web.FeignInterceptor;
import org.github..rest.web.MyErrorAttributes;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author
 * @date 2018/8/8
 * Description :
 */
@Configuration
public class FeignConfig {

    /**
     * 自定义错误解码器
     */
    @Bean
    @Primary
    public Feign.Builder myFeign() {
        return Feign.builder().errorDecoder(new MyErrorDecoder());
    }

    /**
     * 添加request请求的header
     */
    @Bean
    public RequestInterceptor feignInterceptor() {
        return new FeignInterceptor();
    }

    /**
     * 覆盖spring默认的响应消息格式
     */
    @Bean
    public DefaultErrorAttributes defaultRosesErrorAttributes() {
        return new MyErrorAttributes();
    }

}
