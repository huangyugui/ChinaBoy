package com.mss.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 测试实体
 * @author zt
 * @version 20160523
 *
 */
public class DemoPojo extends BasePojo{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 6696906297850793562L;
	
	private Integer id;
	private BigDecimal amount;
	private String remark;
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
	
	
}
