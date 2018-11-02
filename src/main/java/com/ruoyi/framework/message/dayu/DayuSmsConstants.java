package com.ruoyi.framework.message.dayu;

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
	 * <h>模板名称: 短信验证码</h>
	 * <div>模板ID: SMS_10240415</div>
	 * <div>模板内容: 您的验证码为${code}，切勿告知他人。</div>
	 */
	public static final String tempalte_auth_cdoe = "SMS10240415";

	/**
	 * <h>模板名称: 短信验证码</h>
	 * <div>模板ID: SMS_10240415</div>
	 * <div>模板内容: 恭喜您成为金果商城的会员，您的用户名xxx，密码为手机后六位。您可以通过应用商城下载手机APP[金果商城]</div>
	 */
	public static final String tempalte_register_cdoe = "SMS10240421";
	/**
	 * <h>模板名称: 身份验证验证码 </h>
	 * <div>模板ID: SMS_10135561 </div>
	 * <div>模板内容: 验证码${code}，您正在进行${product}身份验证，打死不要告诉别人哦！</div>
	 */
	public static final String tempalte_authentication = "SMS10135561";
	/**
	 * <h>模板名称: 登录确认验证码</h>
	 * <div>模板ID: SMS_10135559</div>
	 * <div>模板内容: 验证码${code}，您正在登录${product}，若非本人操作，请勿泄露。</div>
	 */
	public static final String tempalte_login = "SMS10135559";

	/**
	 * <h>模板名称: 登录异常验证码 </h>
	 * <div>模板ID: SMS_10135558 </div>
	 * <div>模板内容: 验证码${code}，您正尝试异地登录${product}，若非本人操作，请勿泄露。</div>
	 */
	public static final String tempalte_remote_lgin = "SMS10135558";
	/**
	 * <h>模板名称: 用户注册验证码</h>
	 * <div>模板ID: SMS_10135557</div>
	 * <div>模板内容: 验证码${code}，您正在注册成为${product}用户，感谢您的支持！</div>
	 */
	public static final String tempalte_register = "SMS10135557";
	/**
	 * <h>模板名称: 活动确认验证码</h>
	 * <div>模板ID: SMS_10135556</div>
	 * <div>模板内容: 验证码${code}，您正在参加${product}的${item}活动，请确认系本人申请。</div>
	 */
	public static final String tempalte_activi = "SMS10135556";
	/**
	 * <h>模板名称: 修改密码验证码</h>
	 * <div>模板ID: SMS_10135555</div>
	 * <div>模板内容: 验证码${code}，您正在尝试修改${product}登录密码，请妥善保管账户信息。</div>
	 */
	public static final String tempalte_modify_pwd = "SMS10135555";
	/**
	 * <h>模板名称: 修改密码验证码</h>
	 * <div>模板ID: SMS_35465005</div>
	 * <div>模板内容: 家联普惠_忘记密码 验证码：${code}，尊敬的用户，您正在通过注册时绑定的手机号找回密码操作，为防止盗号，请勿泄露。</div>
	 */
	public static final String tempalte_modify_pwd_2 = "SMS35465005";
	/**
	 * <h>模板名称: 找回密码验证码</h>
	 * <div>模板ID: SMS_35575002</div>
	 * <div>模板内容: 家联普惠_修改密码 验证码：${code}，尊敬的用户，您正在通过注册时绑定的手机号修改密码操作，为防止盗号，请勿泄露。</div>
	 */
	public static final String tempalte_find_pwd = "SMS35575002";
	
	/**
	 * <h>模板名称: 自定义模板</h>
	 * <div>模板ID: SMS_35450002</div>
	 * <div>模板内容: {content}</div>
	 * <div>使用COMPANY_SIGN来作为签名</div>
	 */
	public static final String tempalte_custom = "SMS35450002";
	/**
	 * <h>模板名称: 信息变更验证码</h>
	 * <div>模板ID: SMS_10135554</div>
	 * <div>模板内容: 验证码${code}，您正在尝试变更${product}重要信息，请妥善保管账户信息。</div>
	 */
	public static final String tempalte_modify_msg = "SMS10135554";
	/**
	 * <h>模板名称: 审核通过</h>
	 * <div>模板ID: SMS_12660312</div>
	 * <div>尊敬的${name}，恭喜您在家联商户的开店申请已通过审核。</div>
	 */
	public static final String template_confirm_success = "SMS12660312";
	/**
	 * <h>模板名称: 审核失败</h>
	 * <div>模板ID: SMS_36005113</div>
	 * <div>尊敬的${name}，很遗憾您在家联商户的开店申请由于${refuseContent}未通过，请按要求重新提交申请。</div>
	 */
	public static final String template_confirm_error = "SMS36005113";
	/**
	 * <h>模板名称: 积分分成上限</h>
	 * <div>模板ID: SMS_35935213</div>
	 * <div>您好，平台分润积分池余额已达到预警值，如果低于最小积分将不在产生积分分润</div>
	 */
	public static final String template_point_limit = "SMS35935213";

	/**
	 * <h>模板名称: 提醒商家发货</h>
	 * <div>模板ID: </div>
	 * <div>［金果商城］您的店铺还有N个未发货的订单需要处理，快去发货吧</div>
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
	
	public static final String COMPANY_SIGN = "金果商城";
	
	public static final String CONFIRM_SIGN = "店铺审核";
}
