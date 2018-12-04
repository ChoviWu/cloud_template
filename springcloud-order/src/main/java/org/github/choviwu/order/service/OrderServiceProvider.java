package org.github..order.service;

import com.github.pagehelper.PageHelper;
import org.github..api.order.OrderService;
import org.github..common.commons.PageList;
import org.github..common.commons.Paginator;
import org.github..common.constant.RedisConstants;
import org.github..common.exception.ExceptionMessage;
import org.github..common.model.Goods;
import org.github..common.model.GoodsOrder;
import org.github..common.model.UserOrder;
import org.github..common.util.AssertUtil;
import org.github..common.util.StringUtils;
import org.github..order.feign.AuthServiceConsumer;
import org.github..order.mapper.GoodsMapper;
import org.github..order.mapper.GoodsOrderMapper;
import org.github..order.mapper.UserOrderMapper;
import org.github..redis.RedisRepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author
 * @date 2018/8/13
 * Description :
 */
@RestController
public class OrderServiceProvider implements OrderService {

    @Autowired
    UserOrderMapper userOrderMapper;
    @Autowired
    AuthServiceConsumer authServiceConsumer;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsOrderMapper goodsOrderMapper;
    @Autowired
    private RedisRepositoryUser redisRepository;
    @Override
    public UserOrder getOneByUserOrder(String orderNo) {
        return userOrderMapper.getOneByOrderNo(orderNo);
    }

    @Override
    public PageList getListByUserId(Paginator p, Integer userId) {
        PageHelper.startPage(p.getPageNum(),p.getPageSize());
        List list = userOrderMapper.getOneByUserId(userId);
        return new PageList(list);
    }

    @Transactional
    @Override
    public void seckillReward(Integer userId,Integer goodsId) {
        //TODO 是否过滤用户已拍过的
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        AssertUtil.isNullOrEmpty(goods, ExceptionMessage.Order.GOODS_NOT_FOUNT);
        //一个用户一次
//        Integer count = goodsOrderMapper.getGoodsOrderSizeByUserId(userId,goodsId);
//        if(count>0)
//            return;
        Integer num = goods.getGoodsActualNum();
        //已结束
        if(num==0){
            return;
        }
        //减库存
        goods.setGoodsActualNum(--num);
        goodsMapper.updateByPrimaryKey(goods);
        //增加订单号
        UserOrder userOrder = new UserOrder();
        userOrder.setAmount(BigDecimal.ZERO);
        userOrder.setDeleteStatus(0);
        userOrder.setOrderNo(StringUtils.getOrderNo(4));
        userOrder.setUserId(userId);
        userOrder.setCreateTime(new Date());
        userOrder.setOrderStatus(0);
        userOrderMapper.saveOrderReturnId(userOrder);
        GoodsOrder order = new GoodsOrder();
        order.setGoodsId(goodsId);
        order.setOrderId(userOrder.getId());
        goodsOrderMapper.insertSelective(order);
        redisRepository.set(RedisConstants.MUTANGCHUN,goods.getGoodsActualNum().toString());
        //……
    }

    @Override
    public Integer getNumber(Integer goodsId) {
        return goodsMapper.selectByPrimaryKey(goodsId).getGoodsActualNum();
    }
}
