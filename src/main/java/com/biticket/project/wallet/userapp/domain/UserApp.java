package com.biticket.project.wallet.userapp.domain;

import com.biticket.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 手机app的apk文件更新记录表 sys_user_app
 *
 * @author biticket
 * @date 2018-09-25
 */
public class UserApp extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */

    private Integer appId;
    /**
     * 类型 0-钱包 1-商城用户版 2- 商城商家版
     */

    private Integer apkType;
    /**
     * 版本号
     */

    private String versionId;
    /**
     * 版本描述
     */

    private String remark;
    /**
     * 更新时间
     */

    private Date createTime;
    /**
     * 更新人
     */

    private String createBy;
    /**
     * 下载路径
     */

    private String appUrl;
    /**
     * 版本是否需要更新 0-普通更新，1-强制更新
     */

    private Boolean isUpdate;
    /**
     * 0-安卓 1-IOS
     */

    private Integer appType;
    /**
     * 1删除 0正常
     */

    private Boolean delFlag;

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setApkType(Integer apkType) {
        this.apkType = apkType;
    }

    public Integer getApkType() {
        return apkType;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getVersionId() {
        return versionId;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String getRemark() {
        return remark;
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
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public String getCreateBy() {
        return createBy;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("appId" , getAppId())
                .append("apkType" , getApkType())
                .append("versionId" , getVersionId())
                .append("remark" , getRemark())
                .append("createTime" , getCreateTime())
                .append("createBy" , getCreateBy())
                .append("appUrl" , getAppUrl())
                .append("isUpdate" , getIsUpdate())
                .append("appType" , getAppType())
                .append("delFlag" , getDelFlag())
                .toString();
    }
}
