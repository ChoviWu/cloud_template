package org.github..rest.controller.order;

import org.github..common.base.Message;
import org.github..common.constant.RedisConstants;
import org.github..common.exception.ExceptionMessage;
import org.github..common.model.BasUser;
import org.github..common.model.BasUserT;
import org.github..common.util.AssertUtil;
import org.github..redis.RedisRepositoryUser;
import org.github..rest.base.BaseController;
import org.github..rest.service.AuthServiceConsumer;
import org.github..rest.service.MqConsumer;
import org.github..rest.service.UserOrderConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @date 2018/8/14
 * Description :秒杀的控制器
 */
@RestController
@RequestMapping("user/order")
public class SeckillController  extends BaseController{

    @Autowired
    RedisRepositoryUser redisRepository;
    @Autowired
    AuthServiceConsumer authServiceConsumer;
    @Autowired
    MqConsumer mqConsumer;
    @Autowired
    UserOrderConsumer orderConsumer;

    @RequestMapping(value = "seckill")
    public Object seckill(Integer userId){
        BasUserT user =  authServiceConsumer.getUserByKey(userId);
        AssertUtil.isNullOrEmpty(user, ExceptionMessage.User.USER_IS_NULL);
        //拿到商品的数量
        String mutangchunNum =  redisRepository.get(RedisConstants.MUTANGCHUN);
        //库存不足
        AssertUtil.isFalse(mutangchunNum!=null && "0".equals(mutangchunNum),ExceptionMessage.Order.GOODS_NUM_IS_ENOUGH);
//        AssertUtil.isTrue(orderConsumer.getNumber(1)>0,ExceptionMessage.Order.GOODS_NUM_IS_ENOUGH);
//        是否判断重复秒杀
//        long size = redisRepository.listSize(RedisConstants.SECKILL);
//        AssertUtil.isTrue(size<=100,ExceptionMessage.Order.GOODS_NUM_IS_ENOUGH);
//         取出并删除
        redisRepository.leftPush(RedisConstants.SECKILL, String .valueOf(user.getId()));
        String list = redisRepository.leftPop(RedisConstants.SECKILL);
        orderConsumer.seckillReward(1,1);
        //库存量100
//        mqConsumer.sendMessage(RabbitMqTag.EXCHANGE, RabbitMqTag.SECKILLREWARD_KEY + System.currentTimeMillis(), RabbitMqTag.SECKILL_REWARD, JsonUtils.toJson(list));
        return Message.OK;
    }
}
