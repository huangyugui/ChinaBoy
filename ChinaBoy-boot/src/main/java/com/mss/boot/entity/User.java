package com.mss.boot.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="用户对象")
@Table(name="t_user")
public class User implements Serializable{

	private static final long serialVersionUID = 7628493649555448980L;

	@ApiModelProperty(value="唯一主键")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ApiModelProperty(value="登录名称")
	private String name;
	
	@ApiModelProperty(value="登录密码")
	private String password;
	
	@JsonFormat(pattern="yyyyMMdd HH:mm:ss")
	@ApiModelProperty(value="最后一次登录日期")
	@Column(name="lastLoginDate")
	private Date lastLoginDate;
	
	@JsonFormat(pattern="yyyyMMdd HH:mm:ss")
	@ApiModelProperty(value="创建日期")
	@Column(name="createDate")
	private Date createDate;
	
	@JsonFormat(pattern="yyyyMMdd HH:mm:ss")
	@ApiModelProperty(value="更新日期")
	@Column(name="updateDate")
	private Date updateDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", lastLoginDate=" + lastLoginDate
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
	
	
}
