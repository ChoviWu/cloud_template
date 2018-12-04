package org.github..mq.handler;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.github..common.model.BasUser;
import org.github..common.mq.RabbitMqTag;
import org.github..common.util.JsonUtils;
import org.github..common.util.SerializeUtils;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author
 * @date 2018/6/26
 * Description : 测试登录消费者监听
 */
@Component
@Slf4j
public class LoginHandler extends AbstractHandler{

    @RabbitHandler
    @RabbitListener(queues = RabbitMqTag.LOGIN,containerFactory = RabbitMqTag.CONNECTION)
    @Override
    public void onMessage(@Header(RabbitMqTag.LOGIN) Message message) throws Exception {
        log.info("login comming ...");
        BasUser user = this.jackon2ToObject(message,BasUser.class);
        if(user ==null) return;
        synchronized (flag) {
            System.out.println(user);
            flag = true;
        }
    }

}
