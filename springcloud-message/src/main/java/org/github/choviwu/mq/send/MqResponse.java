package org.github..mq.send;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author
 * @date 2018/5/30
 * MQ响应加密类
 */
//@RestControllerAdvice(basePackages = "org.github..mq")
public class MqResponse implements ResponseBodyAdvice<Object> {

    @Autowired
    private MqSendMessageImpl sendMessage;
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {

        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
//        if(methodParameter.getMethod().isAnnotationPresent(RabbitSend.class)){
//            RabbitSend rabbitSend = methodParameter.getMethod().getAnnotation(RabbitSend.class);
//            //clazz
//            Class<?> clazz = rabbitSend.clazz();
//            //exchange
//            String exchange = rabbitSend.exchange();
//            //队列
//            String routKey = rabbitSend.name()=="" ? rabbitSend.value() : rabbitSend.name();
//            String header = rabbitSend.header();
//            AssertUtil.isNullOrEmpty(clazz,"");
//            AssertUtil.isNullOrEmpty(exchange,"");
//            AssertUtil.isNullOrEmpty(routKey,"");
//            AssertUtil.isNullOrEmpty(header,"");
//            Object obj = null;
//
//            try {
//                 obj = clazz.newInstance();
//                AssertUtil.isTrue(!(obj instanceof Serializable),"this_class_not_serializable");
//                sendMessage.sendMessage(exchange,routKey,header,obj);
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
        return o;
    }
}
