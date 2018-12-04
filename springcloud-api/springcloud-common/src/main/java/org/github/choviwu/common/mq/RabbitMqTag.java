package org.github..common.mq;

/**
 * @author
 * @date 2018/6/27
 * Description :
 */
public class RabbitMqTag {

    //=====================tag start==========================
    public final static String LOGIN = "login";
    public final static String SEND_REWARD = "sendReward";
    public final static String ARTICLE_SEND = "articleSend";
    public final static String SECKILL_REWARD = "seckillReward";


    //=====================tag end==========================
    //=====================tag key start========================
    public final static String LOGIN_KEY = "login.";
    public final static String SEND_REWARD_KEY = "sendReward.";
    public final static String ARTICLE_SEND_KEY = "articleSend.";
    public final static String SECKILLREWARD_KEY = "seckillReward.";



    //=====================tag key end==========================
    //routKey
    public final static String TOPIC_COMMON = ".#";
    public final static String TOPIC = "topic";
    public final static String FANOUT = "fanout";
    public final static String DIRECT = "direct";
    public final static String EXCHANGE = "exchange";
    public final static String CONNECTION = "rabbitListenerContainerFactory";

}
