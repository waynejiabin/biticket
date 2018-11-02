/***
 * /// 华信短信 HTTPS版短信发送DEMO,通过aspx接口发送短信
 * /// 开发环境 ：JSE1.8,httpclient4.5.2,windows 10 专业版
 * /// 联系方式 ：346910917@qq.com,18611729367
 * /// 版本：1.1
 * /// 最近修订：2016-12-28
 */

package com.ruoyi.framework.message.yy;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.framework.message.dayu.DayuSmsConfig;
import com.ruoyi.framework.message.dayu.SmsUtil;
import com.ruoyi.framework.message.yy.SSLClient;
import com.ruoyi.framework.message.yy.config.YySmsConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HTTPS版本短信发送DEMO
 */
public class YySmsUtil {
	private static final Logger log = LoggerFactory.getLogger(YySmsUtil.class);
	private static HttpClient httpclient;

	private final static String url = "https://dx.ipyy.net/sms.aspx";
	//url="https://dx.ipyy.net/smsJson.aspx";
/*	private final static  String accountName="AA00086";
	private final static String password="AA0008613";*/

	private static YySmsConfig config;

	public static boolean isOpen;

	public static void init(YySmsConfig config) {
		YySmsUtil.config = config;
		SmsUtil.isOpen = config.getIsOpen();
		log.info(isOpen ? "华信短信开关---------已打开" : "华信短信开关-----------已关闭");
	}
	public static boolean  sendSms(String mobile,String text) throws Exception {
		httpclient = new SSLClient();
		HttpPost post = new HttpPost(config.getUrl());
		//String d =config.getAppKey();
		post.setHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("action","send"));
		nvps.add(new BasicNameValuePair("userid", ""));
		nvps.add(new BasicNameValuePair("account", config.getAppKey()));
		nvps.add(new BasicNameValuePair("password", config.getAppSecrect()));
		nvps.add(new BasicNameValuePair("mobile", mobile));
		nvps.add(new BasicNameValuePair("content", text));
		nvps.add(new BasicNameValuePair("sendTime", ""));
		nvps.add(new BasicNameValuePair("extno", ""));
		post.setEntity(new UrlEncodedFormEntity(nvps,HTTP.UTF_8));
		HttpResponse response = httpclient.execute(post);

		try {
			HttpEntity entity = response.getEntity();
			// 将字符转化为XML
			String returnString=EntityUtils.toString(entity, "UTF-8");
			Document doc = DocumentHelper.parseText(returnString);
			// 获取根节点
			Element rootElt = doc.getRootElement();
			// 获取根节点下的子节点的值
			String returnstatus = rootElt.elementText("returnstatus").trim();

			EntityUtils.consume(entity);
			return "Success".equals(returnstatus)?true:false;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return  false;
		}
	}
}