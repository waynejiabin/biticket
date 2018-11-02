package com.ruoyi.framework.message.dayu;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.ruoyi.common.thirdUtil.JsonUtil;
import com.ruoyi.common.utils.StringUtils;


/**
 * 短信工具类
 *
 * @author cyf
 */
public class SmsUtil {

    private static final Logger log = LoggerFactory.getLogger(SmsUtil.class);
    /**
     * 产品名称:云通信短信API产品,开发者无需替换
     */
    private static final String product = "Dysmsapi";
    /**
     * 产品域名,开发者无需替换
     */
    private static final String domain = "dysmsapi.aliyuncs.com";
    private static DayuSmsConfig config;

    public static boolean isOpen;

    public static void init(DayuSmsConfig config) {
        SmsUtil.config = config;
        SmsUtil.isOpen = config.getIsOpen();
        log.info(isOpen ? "阿里短信开关---------已打开" : "阿里短信开关-----------已关闭");
    }


    /**
     * 发送普通短信 无扩展
     *
     * @param param        参数
     * @param templateCode 模板编号
     * @param signature    签名
     * @param recNum       手机号码 最多200个
     * @return
     */
    public static boolean send(Map<?, ?> param, String templateCode, String signature, String[] recNum) {
        return send(param, templateCode, "", signature, recNum);
    }

    /**
     * 发送普通短信 有扩展
     *
     * @param param        参数
     * @param templateCode 模板编号
     * @param extendStr    扩展信息
     * @param signature    签名
     * @param recNum       手机号码
     * @return
     */
    public static boolean send(Map<?, ?> param, String templateCode, String extendStr, String signature,
                               String[] recNum) {
        return send(param, templateCode, DayuSmsConstants.SMS_NORMAL, "", signature, recNum);
    }

    /**
     * 发送短信
     *
     * @param param        参数
     * @param templateCode 模板编号
     * @param type         短信类型
     * @param extendStr    扩展信息
     * @param signature    签名
     * @param recNum       手机号码
     * @return
     */
    public static boolean send(Map<?, ?> param, String templateCode, String type, String extendStr, String signature,
                               String[] recNum) {
        return send(JsonUtil.object2String(param), templateCode, type, extendStr, signature, recNum);
    }

    /**
     * 发送短信
     *
     * @param param        置换模板通配符的参数-json格式
     * @param recNum       接收手机号
     * @param templateCode 模板编号
     * @return
     */
    public static boolean send(String param, String templateCode, String type, String extendStr, String signature,
                               String[] recNum) {

        Assert.notEmpty(recNum, "手机号码不能为空");
        Assert.isTrue(recNum.length <= 200, "传入的手机号个数不能超过200个， 请自行拆分");

        StringBuffer resNums = new StringBuffer();

        for (String no : recNum) {
            resNums.append(no).append(",");
        }

        return send(param, resNums.substring(0, resNums.length() - 1), templateCode, type, extendStr, signature);
    }

    /**
     * 发送短信
     *
     * @param param        置换模板通配符的参数-json格式
     * @param recNum       接收手机号，以逗号分隔
     * @param templateCode 模板编号
     * @param type
     * @param extendStr
     * @return
     */
    private static boolean send(String param, String recNum, String templateCode, String type, String extendStr,
                                String signature){

        IAcsClient acsClient = getClient();
        SendSmsRequest request = packRequest(param, recNum, templateCode, signature, type, extendStr);
        SendSmsResponse rsp = null;
        Map<?, ?> m = null;
        try {
            rsp = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            log.error("与阿里大鱼通讯失败", e);
            return false;
        }

        m = getResult(rsp);

        String msg = (String) m.get(DayuSmsConstants.param_msg);
        Object codeObj = m.get(DayuSmsConstants.param_code);
        int code = codeObj == null ? 0 : Integer.valueOf(codeObj.toString());

        log.info("返回信息：【" + code + "】" + msg);

        Object status = m.get(DayuSmsConstants.param_success);

        return status == null ? false : (Boolean) status;
    }

/*	public static SendSmsResponse sendSms() throws ClientException {

		//可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		//初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", config.getAppKey(), config.getAppSecrect());
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);


		//hint 此处可能会抛出异常，注意catch
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

		return sendSmsResponse;
	}*/

    /**
     * 装配请求参数
     *
     * @param paramString
     * @param recNum
     * @param templateCode
     * @param signature
     * @param type
     * @param exntendStr
     * @return
     */
    private static SendSmsRequest packRequest(String paramString, String recNum, String templateCode,
                                              String signature, String type, String exntendStr) {
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //request.setHttpContentType(type);
        //必填:待发送手机号
        request.setPhoneNumbers(recNum);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signature);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为 "{\"name\":\"Tom\", \"code\":\"123\"}"
        request.setTemplateParam(paramString);
        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        request.setSmsUpExtendCode(exntendStr);

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        //request.setOutId("yourOutId");

        return request;
    }

    /**
     * 处理返回信息
     *
     * @param response
     * @return
     */
    private static Map<?, ?> getResult(SendSmsResponse response) {
        String result = response.getMessage();

        if (StringUtils.hasValue(result)) {
            log.info(result);
            JSONObject jo = JSON.parseObject(response.getRequestId());
            if (response.getCode() != null && response.getCode().equals("OK")) {

                return processAccess(jo);
            } else {
                return processError(jo);
            }
        }
        return getDefault();
    }


    /**
     * 错误返回信息处理
     *
     * @param errorInfo
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static Map<?, ?> processError(Map errorInfo) {
        Map m = (Map) errorInfo.get(DayuSmsConstants.ERROR_KEY);
        if (m != null) {
            m.put(DayuSmsConstants.param_msg, m.get(DayuSmsConstants.param_sub_msg));
            return m;
        } else {
            return getDefault();
        }
    }

    /**
     * 返回信息处理
     *
     * @param info
     * @return
     */
    @SuppressWarnings("rawtypes")
    private static Map processAccess(Map info) {
        Map m = (Map) info.get(DayuSmsConstants.KEY);
        if (m != null) {
            Map rm = (Map) m.get(DayuSmsConstants.param_result);
            if (rm != null) {
                return rm;
            } else {
                return getDefault();
            }
        } else {
            return getDefault();
        }
    }

    /**
     * 默认返回参数
     *
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static Map getDefault() {
        Map m = new HashMap();
        m.put(DayuSmsConstants.param_code, "1");
        m.put(DayuSmsConstants.param_msg, "未知异常-无返回数据");
        m.put(DayuSmsConstants.param_success, false);
        return m;
    }

    /**
     * 创建连接
     *
     * @return
     */
    private static IAcsClient getClient()  {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", config.getAppKey(), config.getAppSecrect());
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return new DefaultAcsClient(profile);
    }

}
