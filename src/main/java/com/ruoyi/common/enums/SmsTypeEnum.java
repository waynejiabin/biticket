package com.ruoyi.common.enums;

public enum SmsTypeEnum {
    /**短信类型。0-注册短信；1-用户找回密码短信；2-用户交易短信；3-系统短信；5-其他*/
    SMS_REGISTER(0),

    /**找回密码*/
    SMS_FIND_PWD(1),

    /**交易*/
    SMS_TRADE(2),

    SMS_SYSTEM(3),

    SMS_OTHER(5);


    public int getValue() {
        return value;
    }

    private final int value;

    private SmsTypeEnum(int value) {
        this.value = value;
    }
}
