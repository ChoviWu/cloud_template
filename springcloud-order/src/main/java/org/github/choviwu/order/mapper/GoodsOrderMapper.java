package org.github..order.mapper;

import org.apache.ibatis.annotations.Param;
import org.github..common.model.GoodsOrder;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GoodsOrderMapper extends Mapper<GoodsOrder> {

    List<GoodsOrder> getGoodsOrderListByUserId(@Param("userId") Integer userId);

    Integer getGoodsOrderSizeByUserId(@Param("userId") Integer userId,@Param("goodsId") Integer goodsId);
}