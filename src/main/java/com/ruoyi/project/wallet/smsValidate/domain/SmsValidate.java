package com.ruoyi.project.wallet.smsValidate.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.util.Date;

/**
 * 系统短信发送表 sys_sms_validate
 *
 * @author ruoyi
 * @date 2018-08-30
 */
public class SmsValidate extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long smsId;
    /**
     * 短信类型。0-注册短信；1-用户找回密码短信；2-用户交易短信；3-系统短信；5-其他
     */
    private Integer smsType;
    /**
     * 用户ID。
     */
    private Integer userId;
    /**
     * 手机号码
     */
    private String mobileNumber;
    /**
     * 验证码
     */
    private String validateCode;
    /**
     * 短信内容
     */
    private String smsContent;
    /**
     * 失效时间
     */
    private Date expireTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 是否发送。0-未发送，1-发送；
     */
    private Boolean sended;
    /**
     * 是否删除.0-未删除；1-删除
     */
    private Boolean delFlag;

    public void setSmsId(Long smsId) {
        this.smsId = smsId;
    }

    public Long getSmsId() {
        return smsId;
    }

    public void setSmsType(Integer smsType) {
        this.smsType = smsType;
    }

    public Integer getSmsType() {
        return smsType;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setSended(Boolean sended) {
        this.sended = sended;
    }

    public Boolean getSended() {
        return sended;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("smsId" , getSmsId())
                .append("smsType" , getSmsType())
                .append("userId" , getUserId())
                .append("mobileNumber" , getMobileNumber())
                .append("validateCode" , getValidateCode())
                .append("smsContent" , getSmsContent())
                .append("expireTime" , getExpireTime())
                .append("createTime" , getCreateTime())
                .append("sended" , getSended())
                .append("delFlag" , getDelFlag())
                .toString();
    }
}
