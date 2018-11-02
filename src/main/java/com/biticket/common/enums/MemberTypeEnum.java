package com.biticket.common.enums;

/**
 * 用户类型
 * @author Administrator
 */
public enum MemberTypeEnum {
    /**
     * 用户类型。0-普通用户；1-VIP；2-超级VIP；3-合伙人；5-社区联盟
     */
    MEMBER_COMMON(0),

    MEMBER_VIP(1),

    MEMBER_SUPER_VIP(2),

    MEMBER_PARTNER(3),

    MEMBER_COMMUNITY(5);


    public int getValue() {
        return value;
    }

    private final int value;

    private MemberTypeEnum(int value) {
        this.value = value;
    }
}
