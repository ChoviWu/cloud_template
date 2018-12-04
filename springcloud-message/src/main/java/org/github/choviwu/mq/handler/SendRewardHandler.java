package org.github..mq.handler;

import lombok.extern.slf4j.Slf4j;
import org.github..common.model.BasUser;
import org.github..common.mq.RabbitMqTag;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @author
 * @date 2018/6/27
 * Description :
 */
@Component
@Slf4j
public class SendRewardHandler extends AbstractHandler {

    @RabbitHandler
    @RabbitListener(queues = RabbitMqTag.SEND_REWARD,containerFactory = RabbitMqTag.CONNECTION)
    @Override
    public void onMessage(@Header(RabbitMqTag.SEND_REWARD) Message message){
        log.info("sendReward comming ...");
        BasUser user = this.jackon2ToObject(message,BasUser.class);
        if(user ==null) return;
        try {
            synchronized (flag) {
                System.out.println(user);
                flag = true;
            }
        }finally {
            if(flag){
                System.out.println("ACK");
            }else{
                System.out.println("NOT_ACK");
            }
            log.info("sendReward end ...");
        }
    }
}
