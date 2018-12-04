package org.github..mq.handler;

import lombok.extern.slf4j.Slf4j;
import org.github..common.constant.RedisConstants;
import org.github..common.mq.RabbitMqTag;
import org.github..common.util.StringUtils;
import org.github..mq.feign.AuthServiceConsumer;
import org.github..mq.feign.UserOrderConsumer;
import org.github..redis.RedisRepositoryUser;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author
 * @date 2018/8/13
 * Description : 秒杀订单处理
 */
@Component
@Slf4j
public class SeckillHandler extends AbstractHandler {

    @Autowired
    AuthServiceConsumer userService;
    @Autowired
    UserOrderConsumer userOrderConsumer;
    @Autowired
    RedisRepositoryUser redisRepository;

    @RabbitHandler
    @RabbitListener(queues = RabbitMqTag.SECKILL_REWARD,containerFactory = RabbitMqTag.CONNECTION)
    @Override
    public void onMessage(@Header(RabbitMqTag.SECKILL_REWARD)Message message) throws Exception {

        String  userIds  = this.jackon2ToObject(message, String .class);
        if(userIds ==null)
            return;
        //do seckill logic
        Set<String> list = redisRepository.zrange(RedisConstants.SECKILL,0,100);
        if(list==null){
            return;
        }
        for (String  userIdStr : list){
            if(!StringUtils.isNotEmpty(userIdStr)){
                continue;
            }
            try {
                Integer userId = Integer.parseInt(userIdStr);
                userOrderConsumer.seckillReward(userId,1);
            }catch (Exception e){
                log.info(e+"");
                continue;
            }
        }
    }
}
