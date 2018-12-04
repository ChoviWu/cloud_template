package org.github..common.model;

import java.util.Date;
import javax.persistence.*;

public class Goods {
    @Id
    private Integer id;

    @Column(name = "goods_name")
    private String goodsName;

    @Column(name = "goods_num")
    private Integer goodsNum;

    /**
     * 实际的
     */
    @Column(name = "goods_actual_num")
    private Integer goodsActualNum;

    private Date addtime;

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
     * @return goods_name
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * @param goodsName
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * @return goods_num
     */
    public Integer getGoodsNum() {
        return goodsNum;
    }

    /**
     * @param goodsNum
     */
    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    /**
     * 获取实际的
     *
     * @return goods_actual_num - 实际的
     */
    public Integer getGoodsActualNum() {
        return goodsActualNum;
    }

    /**
     * 设置实际的
     *
     * @param goodsActualNum 实际的
     */
    public void setGoodsActualNum(Integer goodsActualNum) {
        this.goodsActualNum = goodsActualNum;
    }

    /**
     * @return addtime
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * @param addtime
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}