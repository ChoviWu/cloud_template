package org.github..api.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.filters.RemoteIpFilter;
import org.github..api.web.WebServletListener;
import org.github..common.util.JsonUtils;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.*;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @date 2018/5/30
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer{



    @Bean
    ServletListenerRegistrationBean registrationBean() {
        ServletListenerRegistrationBean listenerRegistrationBean = new ServletListenerRegistrationBean();
        listenerRegistrationBean.setListener(new WebServletListener());
        return listenerRegistrationBean;
    }
    /**
     * Remote ip filter remote ip filter.
     *
     * @return the remote ip filter
     */
    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }
    //默认为xml   手动配置请求头 html/Json
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON_UTF8);
        configurer.mediaType(MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_JSON_UTF8);
    }
    /**
     * Object mapper object mapper.
     *
     * @return the object mapper
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    /**
     * Http message converter http message converter.
     *
     * @return the http message converter
     */
    @Bean
    public HttpMessageConverter httpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(this.objectMapper());
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON_UTF8);
        converter.setSupportedMediaTypes(list);
        converter.setDefaultCharset(Charset.defaultCharset());
        return converter;
    }
    /**
     * Validator local validator factory bean.
     *
     * @return the local validator factory bean
     */
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    /**
     * Gets method validation post processor.
     *
     * @return the method validation post processor
     */
    @Bean
    public MethodValidationPostProcessor getMethodValidationPostProcessor() {
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        processor.setValidator(validator());
        return processor;
    }
    @Override
    public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
        // 等价于<mvc:default-servlet-handler />, 对静态资源文件的访问, 将无法 mapping 到 Controller 的 path 交给 default servlet handler 处理
        configurer.enable();
    }

    /**
     * Setup Swagger UI <br/>
     * refer: https://github.com/springfox/springfox/issues/1427
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/",
                "classpath:/META-INF/resources/images");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}