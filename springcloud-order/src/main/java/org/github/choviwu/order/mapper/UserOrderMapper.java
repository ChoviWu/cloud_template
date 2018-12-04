package org.github..order.mapper;

import org.apache.ibatis.annotations.Param;
import org.github..common.model.UserOrder;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserOrderMapper extends Mapper<UserOrder> {

    UserOrder getOneByOrderNo(@Param("orderNo")String orderNo);


    List<UserOrder> getOneByUserId(@Param("userId")Integer userId);

    int saveOrderReturnId(UserOrder userOrder);
}