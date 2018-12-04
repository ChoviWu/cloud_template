package org.github..common.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author
 * @since 2018-09-03
 */
@TableName("bas_user_t")
public class BasUserT extends Model<BasUserT> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	@TableField("user_name")
	private String userName;
	private String password;
	private String salt;
	private String phone;
	@TableField("user_type")
	private String userType;
	@TableField("open_id")
	private String openId;
	@TableField("record_time")
	private Date recordTime;
	@TableField("last_login_time")
	private Date lastLoginTime;
	@TableField("last_login_city")
	private String lastLoginCity;
	@TableField("record_addr")
	private String recordAddr;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginCity() {
		return lastLoginCity;
	}

	public void setLastLoginCity(String lastLoginCity) {
		this.lastLoginCity = lastLoginCity;
	}

	public String getRecordAddr() {
		return recordAddr;
	}

	public void setRecordAddr(String recordAddr) {
		this.recordAddr = recordAddr;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "BasUserT{" +
			", id=" + id +
			", userName=" + userName +
			", password=" + password +
			", salt=" + salt +
			", phone=" + phone +
			", userType=" + userType +
			", openId=" + openId +
			", recordTime=" + recordTime +
			", lastLoginTime=" + lastLoginTime +
			", lastLoginCity=" + lastLoginCity +
			", recordAddr=" + recordAddr +
			"}";
	}
}
