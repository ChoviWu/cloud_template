package org.github..rest.service;

import org.github..api.order.OrderService;
import org.github..common.constant.FeignConstants;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author
 * @date 2018/8/13
 * Description :
 */
@FeignClient(value = FeignConstants.ORDER_SERVICE)
public interface UserOrderConsumer extends OrderService {
}
