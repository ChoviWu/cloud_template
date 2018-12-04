package org.github..common.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "user_order")
public class UserOrder {
    @Id
    private Integer id;

    /**
     * 订单号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 购买金额
     */
    private BigDecimal amount;

    /**
     * 1-正常
     */
    @Column(name = "order_status")
    private Integer orderStatus;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "create_ip")
    private String createIp;

    /**
     * 0-未删除 1-已删除
     */
    @Column(name = "delete_status")
    private Integer deleteStatus;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取订单号
     *
     * @return order_no - 订单号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单号
     *
     * @param orderNo 订单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取购买金额
     *
     * @return amount - 购买金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置购买金额
     *
     * @param amount 购买金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取1-正常
     *
     * @return order_status - 1-正常
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置1-正常
     *
     * @param orderStatus 1-正常
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return create_ip
     */
    public String getCreateIp() {
        return createIp;
    }

    /**
     * @param createIp
     */
    public void setCreateIp(String createIp) {
        this.createIp = createIp;
    }

    /**
     * 获取0-未删除 1-已删除
     *
     * @return delete_status - 0-未删除 1-已删除
     */
    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * 设置0-未删除 1-已删除
     *
     * @param deleteStatus 0-未删除 1-已删除
     */
    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}