package com.biticket.project.wallet.memberOrder.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.biticket.framework.web.domain.BaseEntity;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 合伙人单量表 ats_member_order
 *
 * @author biticket
 * @date 2018-09-08
 */
public class MemberOrder extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**  */
    private Long orderId;
    /**
     * 用户ID
     */
    private Integer memberId;
    /**
     * 来源用户ID
     */
    private Integer fromMemberId;
    /**
     * 会员配送量
     */
    private BigDecimal fromMemberAmount;
    /**
     * 单量
     */
    private Integer orderNums;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 删除标志
     */
    private Boolean delFlag;
    private String fromMemberMobile;

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setFromMemberId(Integer fromMemberId) {
        this.fromMemberId = fromMemberId;
    }

    public Integer getFromMemberId() {
        return fromMemberId;
    }

    public void setFromMemberAmount(BigDecimal fromMemberAmount) {
        this.fromMemberAmount = fromMemberAmount;
    }

    public BigDecimal getFromMemberAmount() {
        return fromMemberAmount;
    }

    public void setOrderNums(Integer orderNums) {
        this.orderNums = orderNums;
    }

    public Integer getOrderNums() {
        return orderNums;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setFromMemberMobile(String fromMemberMobile) {
        this.fromMemberMobile = fromMemberMobile;
    }

    public String getFromMemberMobile() {
        return fromMemberMobile;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("orderId", getOrderId())
                .append("memberId", getMemberId())
                .append("fromMemberId", getFromMemberId())
                .append("fromMemberAmount", getFromMemberAmount())
                .append("orderNums", getOrderNums())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("delFlag", getDelFlag())
                .append("fromMemberMobile", getFromMemberMobile())
                .toString();
    }
}

