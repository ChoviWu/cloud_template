package org.github..mq.handler;

import lombok.extern.slf4j.Slf4j;
import org.github..common.model.BasUser;
import org.github..common.mq.RabbitMqTag;
import org.github..common.util.JsonUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @author
 * @date 2018/7/5
 * Description :
 */
@Component
@Slf4j
public class ArticleSendHandler extends AbstractHandler {

    @RabbitHandler
    @RabbitListener(queues = RabbitMqTag.ARTICLE_SEND,containerFactory = RabbitMqTag.CONNECTION)
    @Override
    public void onMessage(@Header(RabbitMqTag.ARTICLE_SEND) Message message) throws Exception {
        log.info("articleSend comming ...");
        BasUser user = this.jackon2ToObject(message,BasUser.class);
        if(user==null) return;
        synchronized (flag) {
            System.out.println(user);
            flag = true;
        }
    }
}
