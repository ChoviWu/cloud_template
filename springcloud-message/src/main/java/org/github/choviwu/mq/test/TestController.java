package org.github..mq.test;

import org.github..common.model.BasUser;
import org.github..common.mq.RabbitMqTag;
import org.github..common.util.JsonUtils;
import org.github..mq.send.MqSendMessageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author
 * @date 2018/6/26
 * Description :
 */
@RestController
public class TestController {

    @Autowired
    MqSendMessageImpl sendMessage;

    @RequestMapping(value = "send")
    public Object send(BasUser user){
        user.setUserName("CCCCCC");
        sendMessage.sendMessage(RabbitMqTag.EXCHANGE,RabbitMqTag.LOGIN_KEY+System.currentTimeMillis(), RabbitMqTag.LOGIN, JsonUtils.toJson(new BasUser()));
//        sendMessage.sendMessage(RabbitMqTag.EXCHANGE,RabbitMqTag.SEND_REWARD_KEY+System.currentTimeMillis(),"reward",new BasUser());
        return user;
    }

}
