package org.github..mq.send;

import org.github..api.mq.MqSendMessage;
import org.github..common.exception.ExceptionMessage;
import org.github..common.model.BusMessageLog;
import org.github..common.util.*;
import org.github..mq.mapper.BusMessageLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

/**
 * Created by  on 2018/04/11
 * Description:发消息 核心
 */

@RestController
public class MqSendMessageImpl implements MqSendMessage {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private  RabbitTemplate rabbitTemplate;

    private final BusMessageLogMapper messageLogMapper;

    @Autowired
    public MqSendMessageImpl(BusMessageLogMapper messageLogMapper){
        this.messageLogMapper = messageLogMapper;
    }

    /**
     * 发消息 TOPIC  消息体必须实现序列化
     * @param exchangeKey
     * @param queueKey
     * @param header
     * @param object
     */
    @Override
    public void sendMessage(final String exchangeKey,
                            final String queueKey,
                            final String header,
                            final Object object){
        try {
            if(object==null) return;
            //消息体防重复 唯一
            CorrelationData data = new CorrelationData(UUID.randomUUID().toString());
            AssertUtil.isTrue(messageLogMapper.getLogByCrc32Code(data.getId())==null,ExceptionMessage.Sys.SYS_ERROR);
            this.insertLog(exchangeKey,data.getId(),queueKey,object);
            rabbitTemplate.convertAndSend(exchangeKey, queueKey,object,(Message message) ->{
                        message.getMessageProperties().setHeader(header, object);
                        //发消息 延迟消费过期不消费
//                        message.getMessageProperties().setExpiration("");
                        return message;
                }, data);
        }catch (Exception e){
            logger.info(JsonUtils.toJson(e));
        }
    }

    /**
     * 插入消息
     * @param exchangeKey   类型
     * @param key           key  exchange才有
     * @param message       消息体
     */
    private void insertLog(final String exchangeKey,
                           final String  crc32Code,
                           final String key,
                           final Object message){

        BusMessageLog messageLog = new BusMessageLog();
        messageLog.setBody(JsonUtils.toJson(message));
        messageLog.setAddip(IpUtils.get());
        messageLog.setAddtime(new Date());
        messageLog.setRoutKey(key);
        messageLog.setStatus(0);
        messageLog.setCrc32code(crc32Code);
        if(StringUtils.isEmpty(exchangeKey)){
            messageLog.setType(2);
        }else{
            messageLog.setType(1);
        }
        AssertUtil.isTrue(messageLogMapper.insert(messageLog) > 0, ExceptionMessage.Sys.SYS_INSERT_ERROR);
    }

}
