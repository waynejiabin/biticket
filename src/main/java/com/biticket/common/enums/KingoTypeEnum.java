package com.biticket.common.enums;

/**
 * 金果数据状态
 * @author Administrator
 */
public enum KingoTypeEnum {
    /** 1-解冻；2-加速释放；3-转入；4-转出；5-其他*/
    KINGO_SYSTEM(0,"系统配送","收入：配送"),
    /**解冻*/
    KINGO_RELEASE(1,"锁仓解冻产生","解冻：解冻"),

    KINGO_RELEASE_ACCELERATED(2,"加速解冻产生","解冻：加速"),
    /**进项*/
    KINGO_IN(3,"转入产生","收入：转入"),
    /**出项*/
    KINGO_OUT(4,"转出消耗","转出：转出"),

    /**进项:推荐奖励*/
    KINGO_RECOMMEND_IN(5,"推荐奖励产生","收入：推荐"),
    /**进项:推荐奖励*/
    KINGO_RECOMMEND_CONFIG(9,"系统赠送","收入：赠送");

    private int type;

    private String remark;

    private String platformRemark ;

    KingoTypeEnum(int type, String remark, String platformRemark) {
        this.type = type;
        this.remark = remark;
        this.platformRemark = platformRemark;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPlatformRemark() {
        return platformRemark;
    }

    public void setPlatformRemark(String platformRemark) {
        this.platformRemark = platformRemark;
    }

}


