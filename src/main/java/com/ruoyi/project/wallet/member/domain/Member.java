package com.ruoyi.project.wallet.member.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员表 ats_member
 *
 * @author ruoyi
 * @date 2018-08-25
 */
public class Member extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer memberId;
    /**
     * 登录名
     */
    @Excel(name = "登录名")
    private String loginName;
    /**
     * 密码
     */
    private String memberPassword;
    /**  */
    private String salt;
    /**
     * 交易密码
     */
    private String tradePassword;
    /**
     * 昵称
     */
    @Excel(name = "昵称")
    private String nickName;
    /**
     * 用户类型。0-普通用户；1-VIP；2-超级VIP；3-合伙人；5-社区联盟
     */
    @Excel(name = "用户类型")
    private Integer memberType;
    /**
     * 用户状态。1-正常；0-冻结；2-其他
     */
    @Excel(name = "用户状态")
    private Integer memberStatus;
    /**
     * 手机号码
     */
    @Excel(name = "手机号码")
    private String memberMobile;
    /**
     * 推荐人ID
     */
    private Integer pId;
    /**
     * 合伙人ID
     */
    private Integer partnerId;
    /**
     * 社区联盟账号ID
     */
    private Integer communityId;
    /**
     * 推荐人
     */
    @Excel(name = "推荐人")
    private String parenter;
    /**
     * 合伙人
     */
    @Excel(name = "合伙人")
    private String partner;
    /**
     * 社区联盟
     */
    @Excel(name = "社区联盟")
    private String community;
    /**
     * 总资产
     */
    @Excel(name = "总资产")
    private BigDecimal amountTotal;
    /**
     * 活动资产
     */
    @Excel(name = "活动资产")
    private BigDecimal amountAvailable;
    /**
     * 冻结资产
     */
    @Excel(name = "锁仓资产")
    private BigDecimal amountFrozen;
    /**
     * 配置资产。用来进行每天静态释放的参考量。
     */
    private BigDecimal amountConfig;
    /**
     * 钱包地址。hash生成。预留字段
     */
    @Excel(name = "钱包地址")
    private String walletAddress;
    /**
     * 创建时间
     */
    @Excel(name = "创建时间")
    private Date createTime;
    /**
     * 最后登陆IP
     */
    @Excel(name = "最后登陆IP")
    private String loginIp;
    /**
     * 最后登录时间
     */
    @Excel(name = "最后登录时间")
    private Date loginTime;
    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;
    /**
     * 数据状态。0-正常；1-删除
     */
    private Boolean delFlag;





    public String getParenter() {
        return parenter;
    }

    public void setParenter(String parenter) {
        this.parenter = parenter;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSalt() {
        return salt;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword;
    }

    public String getTradePassword() {
        return tradePassword;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setMemberType(Integer memberType) {
        this.memberType = memberType;
    }

    public Integer getMemberType() {
        return memberType;
    }

    public void setMemberStatus(Integer memberStatus) {
        this.memberStatus = memberStatus;
    }

    public Integer getMemberStatus() {
        return memberStatus;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }

    public String getMemberMobile() {
        return memberMobile;
    }

    public void setPId(Integer pId) {
        this.pId = pId;
    }

    public Integer getPId() {
        return pId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountAvailable(BigDecimal amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public BigDecimal getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountFrozen(BigDecimal amountFrozen) {
        this.amountFrozen = amountFrozen;
    }

    public BigDecimal getAmountFrozen() {
        return amountFrozen;
    }

    public void setAmountConfig(BigDecimal amountConfig) {
        this.amountConfig = amountConfig;
    }

    public BigDecimal getAmountConfig() {
        return amountConfig;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("memberId", getMemberId())
                .append("loginName", getLoginName())
                .append("memberPassword", getMemberPassword())
                .append("salt", getSalt())
                .append("tradePassword", getTradePassword())
                .append("nickName", getNickName())
                .append("memberType", getMemberType())
                .append("memberStatus", getMemberStatus())
                .append("memberMobile", getMemberMobile())
                .append("pId", getPId())
                .append("partnerId", getPartnerId())
                .append("communityId", getCommunityId())
                .append("amountTotal", getAmountTotal())
                .append("amountAvailable", getAmountAvailable())
                .append("amountFrozen", getAmountFrozen())
                .append("amountConfig", getAmountConfig())
                .append("walletAddress", getWalletAddress())
                .append("createTime", getCreateTime())
                .append("loginIp", getLoginIp())
                .append("loginTime", getLoginTime())
                .append("remark", getRemark())
                .append("delFlag", getDelFlag())
                .toString();
    }

    /**
     * 生成随机盐
     */
    public void randomSalt()
    {
        // 一个Byte占两个字节，此处生成的3字节，字符串长度为6
        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
        String hex = secureRandom.nextBytes(3).toHex();
        setSalt(hex);
    }

}
