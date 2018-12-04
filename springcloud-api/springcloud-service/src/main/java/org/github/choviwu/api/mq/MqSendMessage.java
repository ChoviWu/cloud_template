package org.github..api.mq;


import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by  on 2018/04/11
 * Description:发消息 核心
 */
@Component
public interface MqSendMessage {

    //发消息 TOPIC  消息体必须实现序列化
    @RequestMapping("mq/send")
    public void sendMessage(@RequestParam("exchangeKey") String exchangeKey,
                            @RequestParam("queueKey") String queueKey,
                            @RequestParam("header") String header,
                            @RequestParam("object") Object object);

}
