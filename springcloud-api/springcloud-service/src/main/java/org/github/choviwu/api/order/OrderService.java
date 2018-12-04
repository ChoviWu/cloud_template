package org.github..api.order;

import org.apache.ibatis.annotations.Param;
import org.github..common.commons.PageList;
import org.github..common.commons.Paginator;
import org.github..common.model.UserOrder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author
 * @date 2018/8/13
 * Description :
 */
@RequestMapping("api")
public interface OrderService {

    @RequestMapping(value = "order/getOneByUserOrder")
    UserOrder getOneByUserOrder(String  orderNo);
    @RequestMapping(value = "order/getListByUserId")
    PageList getListByUserId(Paginator p,@RequestParam("userId") Integer  userId);

    @RequestMapping(value = "order/seckill")
    void seckillReward(@RequestParam("userId") Integer userId,@RequestParam("goodsId")Integer goodsId);

    @RequestMapping(value = "order/getNumber")
    Integer getNumber(@RequestParam("goodsId")Integer goodsId);
}
