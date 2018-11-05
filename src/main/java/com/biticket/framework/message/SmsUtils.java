package com.biticket.framework.message;

import com.biticket.framework.message.dayu.DayuSmsUtil;
import com.biticket.framework.message.yy.YySmsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class SmsUtils {

    private static final Logger log = LoggerFactory.getLogger(SmsUtils.class);

    /***
     * 发送短信
     * @param param
     * @param templateCode
     * @param recNum
     * @return
     */
    public static boolean sendSms(Map<String, Object> param, String templateCode, String[] recNum) {
        //if(DayuSmsUtil.isOpen){
           return  DayuSmsUtil.send(param,templateCode,recNum);
       /* } else if(YySmsUtil.isOpen){
            String mobile =String.join(",",recNum);
            try {
                return  YySmsUtil.sendSms(mobile,templateCode);
            } catch (Exception e) {
                e.printStackTrace();
                return  false;
            }
        }
        return  false;*/
    }


}
