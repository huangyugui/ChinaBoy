package com.mss.pojo;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 测试实体
 * @author zt
 * @version 20160523
 *
 */
@ApiModel(value="Demo对象")
public class DemoPojo extends BasePojo{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 6696906297850793562L;
	
	@ApiModelProperty(value="demoId")
	private Integer id;
	@ApiModelProperty(value="demo金额")
	private BigDecimal amount;
	@ApiModelProperty(value="demo描述")
	private String remark;
	@ApiModelProperty(value="demo创建日期")
	private Date createDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DemoPojo other = (DemoPojo) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "DemoPojo [id=" + id + ", amount=" + amount + ", remark="
				+ remark + ", createDate=" + createDate + "]";
	}
	
	
}
