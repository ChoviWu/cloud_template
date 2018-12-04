package org.github..rest.service;

import org.github..api.mq.MqSendMessage;
import org.github..common.constant.FeignConstants;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author
 * @date 2018/8/13
 * Description :
 */
@FeignClient(FeignConstants.MQ_SERVICE)
public interface MqConsumer extends MqSendMessage {
}
