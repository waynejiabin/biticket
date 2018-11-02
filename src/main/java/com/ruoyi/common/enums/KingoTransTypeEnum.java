package com.ruoyi.common.enums;

/**
 * 金果记账类型 1 进账 2 出账;3-解冻
 *
 * @author Administrator
 */
public  enum KingoTransTypeEnum {
    /**
     * 转入
     */
    KINGO_TRANS_IN(1, "已经转入"),
    /**
     * 转出
     */
    KINGO_TRANS_OUT(2, "已经转出"),
    /**
     * 释放
     */
    KINGO_TRANS_RELEASE(3, "系统释放"),

    /**
     * 转入待确认
     */
    KINGO_TRANS_IN_CONFIRM(5, "转入待确认"),

    /**
     * 转出待确认
     */
    KINGO_TRANS_OUT_CONFIRM(8, "转出待确认"),


    KINGO_TRANS_RECLAIM_CONFIRM(12, "系统回收待确定"),
    /**
     * 系统回收手续费
     */
    KINGO_TRANS_RECLAIM(13, "系统回收");


    private  Integer value;

    private String remark;

    KingoTransTypeEnum(int value, String remark) {
        this.value = value;
        this.remark = remark;
    }

    public Integer getValue() {
        return value;
    }

    public String getRemark() {
        return remark;
    }
}
