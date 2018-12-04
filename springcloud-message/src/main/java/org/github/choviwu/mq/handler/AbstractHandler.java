package org.github..mq.handler;

import lombok.extern.slf4j.Slf4j;
import org.github..common.model.BasUser;
import org.github..common.util.JsonUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractHandler implements MqHandler{

    protected  Boolean flag = false;
    /**
     * RabbitMQ unSerializable
     * @param message   MQ
     * @param <T>       T
     * @return
     */
    public  <T> T jackon2ToObject(Message message,Class<T> clazz){
        try {

            T t = (T) new Jackson2JsonMessageConverter().fromMessage(message);
            if(t ==null) return null;
            t = JsonUtils.json2Object(t.toString(),clazz);
            if(t ==null) return null;
            return t;
        }catch (Exception e) {
            return null;
        }
    }
       @Override
       public abstract void onMessage(Message message) throws Exception;

}
