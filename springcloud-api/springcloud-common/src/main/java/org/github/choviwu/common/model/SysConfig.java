package org.github..common.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "sys_config_t")
public class SysConfig implements Serializable{
    @Id
    private Integer id;

    /**
     * 参数
     */
    private String param;

    /**
     * 值
     */
    private String value;

    /**
     * 标注
     */
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
     * 获取参数
     *
     * @return param - 参数
     */
    public String getParam() {
        return param;
    }

    /**
     * 设置参数
     *
     * @param param 参数
     */
    public void setParam(String param) {
        this.param = param;
    }

    /**
     * 获取值
     *
     * @return value - 值
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置值
     *
     * @param value 值
     */
    public void setValue(String value) {
        this.value = value;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}