package org.github..mq.conf;

import org.github..common.base.Constants;
import org.github..common.exception.ExceptionMessage;
import org.github..common.model.BusMessageLog;
import org.github..common.util.AssertUtil;
import org.github..mq.mapper.BusMessageLogMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author
 * @date 2018/6/26
 * Description :
 */
@Configuration
@EnableRabbit
public class RabbitConfig extends RabbitAutoConfiguration{

    @Value("${spring.rabbitmq.host}")
    private String addresses;
    @Value("${spring.rabbitmq.port}")
    private String port;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;
    @Value("${spring.rabbitmq.publisher-confirms}")
    private boolean publisherConfirms;
    @Value("${spring.rabbitmq.publisher-returns}")
    private boolean publisherReturns;


    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public ConnectionFactory rabbitConnectionFactory() {
        CachingConnectionFactory rabbitConnectionFactory = new CachingConnectionFactory();
        rabbitConnectionFactory.setAddresses(addresses+":"+port);
        rabbitConnectionFactory.setUsername(username);
        rabbitConnectionFactory.setPassword(password);
        rabbitConnectionFactory.setVirtualHost(virtualHost);
        rabbitConnectionFactory.setPublisherReturns(publisherReturns);
        /** 如果要进行消息回调，则这里必须要设置为true */
        rabbitConnectionFactory.setPublisherConfirms(publisherConfirms);
        return rabbitConnectionFactory;
    }

    @Bean
    /** 因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置 */
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate(final Jackson2JsonMessageConverter jsonMessageConverter,
                                         final ConnectionFactory rabbitConnectionFactory,
                                         final BusMessageLogMapper messageLogMapper) {
        RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory());
        template.setMessageConverter(jsonMessageConverter);
        template.setMandatory(true);
        template.setConfirmCallback((CorrelationData correlationData, boolean flag, String s)-> {
                BusMessageLog log = messageLogMapper.getLogByCrc32Code(correlationData.getId());
                //消息丢失  确实失败
            AssertUtil.isNullOrEmpty(log, ExceptionMessage.Sys.SYS_ERROR);
                if(flag){
                    //TODO
                    if (log.getStatus().intValue()== Constants.ZERO){
                        //未消费的 让它消费
                        log.setStatus(1);
                        log.setAuditime(new Date());
                        AssertUtil.isTrue(messageLogMapper.updateByPrimaryKeySelective(log) > 0, ExceptionMessage.Sys.SYS_UPDATE_ERROR);
                    }
                }else{
                    //服务器异常  消息
                    /**
                     * 1、 exchange 没有到  false
                     * 2、
                     */
                    //TODO  Deal Logic
                }
            });
        template.setReturnCallback((Message message, int i, String s, String s1, String s2) -> {
                System.out.println("This is Return Back Logic");
                System.out.println(message);
                if(message.getMessageProperties().getRedelivered()){
//                    message.getMessageProperties().ret
                }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                //send
//            rabbitTemplate.send(message);
        });
        template.setConnectionFactory(rabbitConnectionFactory);
        //设置重试机制
        return template;
    }

    // 接收消息的格式转换器
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory rabbitConnectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(rabbitConnectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }
}
