package com.biticket.framework.message.dayu;

public class DayuSmsConstants {
	/**
	 * 错误返回字段名称
	 */
	public static final String ERROR_KEY = "error_response";
	/**
	 * 正确返回字段名称
	 */
	public static final String KEY = "alibaba_aliqin_fc_sms_num_send_response";
	/**
	 * 环境类型-开发
	 */
	public static final String DEV = "develop";
	/**
	 * 环境类型-生产
	 */
	public static final String PRO = "product";
	/**
	 * 短信类型
	 */
	public static final String SMS_NORMAL = "normal";
	/**
	 * 扩展信息
	 */
	public static final String EXTENDS = "";
	
	/**
	 * 自定义信息
	 */
	public final static String tempalte_diy="SMS_10000001";

	/**
	 * 用户注册验证码
	 */
	public static final String  Tempalte_register_cdoe= "SMS_150460256";

	/**
	 *修改密码验证码
	 * */
	public static final String tempalte_findPWD_cdoe = "SMS_150460255";
	/**
	 * 交易操作
	 */
	public static final String tempalte_biticket_trade= "SMS_150183991";


	/**
	 *
	 */
	public static final String tempalte_remote_lgin = "SMS10135558";
	/**
	 *
	 */
	public static  final  String  template_need_goods = "SMS10240420";

	/** ===================== 阿里大鱼返回参数 =============== */
	public static final String param_success = "success";
	public static final String param_msg = "msg";
	public static final String param_code = "code";
	public static final String param_sub_msg = "sub_msg";
	public static final String param_result = "result";
	public static final String param_item = "item";
	
	public static final String LOGIN_SIGN = "登录验证";
	
	public static final String REGISTER_SIGN = "注册验证";
	
	public static final String AUTH_SIGN = "身份验证";
	
	public static final String ACTIVI_SIGN = "活动验证";
	
	public static final String CHANGE_SIGN = "变更验证";
	
	public static final String COMPANY_SIGN = "态阳网络科技";
	
	public static final String CONFIRM_SIGN = "店铺审核";
}
