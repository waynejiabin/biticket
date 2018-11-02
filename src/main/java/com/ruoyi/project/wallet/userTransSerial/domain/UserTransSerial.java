package com.ruoyi.project.wallet.userTransSerial.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 交易流水表 ats_user_trans_serial
 * 
 * @author ruoyi
 * @date 2018-08-24
 */
public class UserTransSerial extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long transId;
	/** 交易单号 */
	private String dealNo;
	/** 用户ID */
	private Integer userId;
	/** 用户昵称 */
	private String userName;
	/** 用户类型。0-普通用户；1-VIP；2-超级VIP；3-合伙人；5-社区联盟 */
	private Integer userType;
	/** 手机 */
	private String userMobile;
	/** 待交易量。预留 */
	private BigDecimal preAmount;
	/** 变动的数量 */
	private BigDecimal amount;
	/** 备注 */
	private String amountRemark;
	/** 创建时间 */
	private Date amountDate;
	/** 记账类型 1 进账 2 出账 */
	private Integer transStatus;
	/** 数据是否删除。0-未删除；1-删除。 */
	private Boolean delFlag;

	/** 操作类型。1-解冻；2-加速释放；3-转入；4-转出；5-其他 */
	private Integer amountType;

	public void setTransId(Long transId) 
	{
		this.transId = transId;
	}

	public Long getTransId() 
	{
		return transId;
	}
	public void setDealNo(String dealNo) 
	{
		this.dealNo = dealNo;
	}

	public String getDealNo() 
	{
		return dealNo;
	}
	public void setUserId(Integer userId) 
	{
		this.userId = userId;
	}

	public Integer getUserId() 
	{
		return userId;
	}
	public void setUserName(String userName) 
	{
		this.userName = userName;
	}

	public String getUserName() 
	{
		return userName;
	}
	public void setUserType(Integer userType) 
	{
		this.userType = userType;
	}

	public Integer getUserType() 
	{
		return userType;
	}
	public void setUserMobile(String userMobile) 
	{
		this.userMobile = userMobile;
	}

	public String getUserMobile() 
	{
		return userMobile;
	}
	public void setPreAmount(BigDecimal preAmount) 
	{
		this.preAmount = preAmount;
	}

	public BigDecimal getPreAmount() 
	{
		return preAmount;
	}
	public void setAmount(BigDecimal amount) 
	{
		this.amount = amount;
	}

	public BigDecimal getAmount() 
	{
		return amount;
	}
	public void setAmountRemark(String amountRemark) 
	{
		this.amountRemark = amountRemark;
	}

	public String getAmountRemark() 
	{
		return amountRemark;
	}
	public void setAmountDate(Date amountDate) 
	{
		this.amountDate = amountDate;
	}

	public Date getAmountDate() 
	{
		return amountDate;
	}
	public void setTransStatus(Integer transStatus) 
	{
		this.transStatus = transStatus;
	}

	public Integer getTransStatus() 
	{
		return transStatus;
	}
	public void setDelFlag(Boolean delFlag) 
	{
		this.delFlag = delFlag;
	}

	public Boolean getDelFlag() 
	{
		return delFlag;
	}
	public Integer getAmountType() {
		return amountType;
	}

	public void setAmountType(Integer amountType) {
		this.amountType = amountType;
	}


	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("transId", getTransId())
            .append("dealNo", getDealNo())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("userType", getUserType())
            .append("userMobile", getUserMobile())
            .append("preAmount", getPreAmount())
            .append("amount", getAmount())
            .append("amountRemark", getAmountRemark())
            .append("amountDate", getAmountDate())
            .append("transStatus", getTransStatus())
            .append("delFlag", getDelFlag())
			.append("amountType", getAmountType())
            .toString();
    }
}
