/// HTTPS版短信发送DEMO,通过aspx接口查询短信余额及发送量
/// 开发环境 ：JSE1.8,httpclient4.5.2,windows 10 专业版
/// 联系方式 ：346910917@qq.com,18611729367
/// 版本：1.1
/// 最近修订：2016-12-28

package com.biticket.framework.message.yy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.biticket.framework.message.yy.common.MD5;
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

/**
 * HTTPS版本短信余额及发送量DEMO
 */
public class BalanceGetViaAspx {

	private static HttpClient httpclient;
	
	public final static void main(String[] args) throws Exception {
		httpclient = new SSLClient();
		String url = "https://dx.ipyy.net/sms.aspx";
		String accountName="AA00086";	//改为实际账号名
		String password="AA0008613";         //改为实际发送密码
		SimpleDateFormat df=new SimpleDateFormat("MMddHHmmss");		
		String Stamp = df.format(new Date());
		String Secret=MD5.GetMD5Code(password+Stamp).toUpperCase();
		
		
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("action","overage"));
		nvps.add(new BasicNameValuePair("userid", accountName));
		nvps.add(new BasicNameValuePair("account", accountName)); 	
		nvps.add(new BasicNameValuePair("password", password));		
		
	
		post.setEntity(new UrlEncodedFormEntity(nvps,HTTP.UTF_8));
	
		HttpResponse response = httpclient.execute(post);
	
		try {
			System.out.println(response.getStatusLine());
			HttpEntity entity = response.getEntity();
			// 将字符转化为XML
			String returnString=EntityUtils.toString(entity, "UTF-8");
			Document doc = DocumentHelper.parseText(returnString);
			// 获取根节点
			Element rootElt = doc.getRootElement();
			// 获取根节点下的子节点的值
			String returnstatus = rootElt.elementText("returnstatus").trim();
			String message = rootElt.elementText("message").trim();
			String payinfo = rootElt.elementText("payinfo").trim();
			String overage = rootElt.elementText("overage").trim();
			String sendTotal = rootElt.elementText("sendTotal").trim();
	
			System.out.println(returnString);
			System.out.println("返回状态为：" + returnstatus);
			System.out.println("返回信息提示：" + message);
	        System.out.println("返回支付方式：" + payinfo);
			System.out.println("返回余额：" + overage);
			System.out.println("返回总点数：" + sendTotal);
			
			EntityUtils.consume(entity);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}