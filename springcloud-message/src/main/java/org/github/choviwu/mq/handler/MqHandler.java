package org.github..mq.handler;

import org.springframework.amqp.core.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @author
 * @date 2018/7/5
 * Description :
 */
public interface MqHandler {

    void onMessage(Message message) throws Exception;
}
