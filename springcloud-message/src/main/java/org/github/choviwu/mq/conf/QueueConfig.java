package org.github..mq.conf;
import lombok.extern.slf4j.Slf4j;
import org.github..common.mq.RabbitMqTag;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by  on 2018/04/11
 * Description: Message Queue Manager
 */
@Configuration
@Slf4j
public class QueueConfig {


    //===============topic start==========
    /**
     * Declear Queue  remark queue in rabbitAdmin
     */
    @Bean
    public Queue login(RabbitAdmin rabbitAdmin){
        Queue queue = new Queue(RabbitMqTag.LOGIN,true,false,false);
        rabbitAdmin.declareQueue(queue);
        log.info("Topic login over");
        return queue;
    }

    /**
     * Declear Queue  remark queue in rabbitAdmin
     */
    @Bean
    public Queue sendReward(RabbitAdmin rabbitAdmin){
        log.info("Topic sendReward over");
        Queue queue = QueueBuilder.durable(RabbitMqTag.SEND_REWARD).build();
        rabbitAdmin.declareQueue(queue);
        return queue;
    }
    /**
     * Declear Queue  remark queue in rabbitAdmin
     */
    @Bean
    public Queue articleSend(RabbitAdmin rabbitAdmin){
        log.info("Topic articleSend over");
        Queue queue = QueueBuilder.durable(RabbitMqTag.ARTICLE_SEND).build();
        rabbitAdmin.declareQueue(queue);
        return queue;
    }
    /**
     * Declear Queue  remark queue in rabbitAdmin
     */
    @Bean
    public Queue seckillReward(RabbitAdmin rabbitAdmin){
        log.info("Topic seckillReward over");
        Queue queue = QueueBuilder.durable(RabbitMqTag.SECKILL_REWARD).build();
        rabbitAdmin.declareQueue(queue);
        return queue;
    }
    //===============queue end ==========

    //===============exchange start ==========
    /**
     * topic exchange
     * @param rabbitAdmin
     * @return
     */
    @Bean
    public TopicExchange topicExchange(RabbitAdmin rabbitAdmin){
        TopicExchange contractTopicExchange = new TopicExchange(RabbitMqTag.EXCHANGE);
        rabbitAdmin.declareExchange(contractTopicExchange);
        log.info("topic exchange bean over");
        return contractTopicExchange;
    }

    /**
     * bind Queue exchange、routKey
     * @param rabbitAdmin
     * @param login           login queue
     * @param topicExchange  topic type default topicExchange
     * @return
     */
    @Bean
    public Binding binding(RabbitAdmin rabbitAdmin,
                           Queue login,
                           TopicExchange topicExchange){
        Binding binding = BindingBuilder.bind(login).to(topicExchange).with(login.getName()+RabbitMqTag.TOPIC_COMMON);
        rabbitAdmin.declareBinding(binding);
        log.info(login.getName() +" bind "+topicExchange.getName()+" OK ");
        return binding;
    }
    /**
     * bind Queue exchange、routKey
     * @param rabbitAdmin
     * @param articleSend           articleSend queue
     * @param topicExchange  topic type default topicExchange
     * @return
     */
    @Bean
    public Binding bindingArticleSend(RabbitAdmin rabbitAdmin,
                           Queue articleSend,
                           TopicExchange topicExchange){
        Binding binding = BindingBuilder.bind(articleSend).to(topicExchange).with(articleSend.getName()+RabbitMqTag.TOPIC_COMMON);
        rabbitAdmin.declareBinding(binding);
        log.info(articleSend.getName() +" bind "+topicExchange.getName()+" OK ");
        return binding;
    }
    /**
     * bind Queue exchange、routKey
     * @param rabbitAdmin
     * @param seckillReward           seckillReward queue
     * @param topicExchange  topic type default topicExchange
     * @return
     */
    @Bean
    public Binding seckillRewardBinding(RabbitAdmin rabbitAdmin,
                           Queue seckillReward,
                           TopicExchange topicExchange){
        Binding binding = BindingBuilder.bind(seckillReward).to(topicExchange).with(seckillReward.getName()+RabbitMqTag.TOPIC_COMMON);
        rabbitAdmin.declareBinding(binding);
        log.info(seckillReward.getName() +" bind "+topicExchange.getName()+" OK ");
        return binding;
    }

    //===============exchange end 的队列==========
    /**
     * bind Queue exchange、routKey
     * @param rabbitAdmin
     * @param sendReward           sendReward queue
     * @param topicExchange  topic type default topicExchange
     * @return
     */
    @Bean
    public Binding bindingReward(RabbitAdmin rabbitAdmin, Queue sendReward,
                                   TopicExchange topicExchange){
        Binding bindingReward = BindingBuilder.bind(sendReward).to(topicExchange).with(sendReward.getName()+RabbitMqTag.TOPIC_COMMON);
        rabbitAdmin.declareBinding(bindingReward);
        log.info(sendReward.getName() +" bind "+topicExchange.getName()+" OK ");
        return bindingReward;
    }
    /**
     * @param rabbitConnectionFactory
     * @return
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory rabbitConnectionFactory){
        return new RabbitAdmin(rabbitConnectionFactory);
    }

}
