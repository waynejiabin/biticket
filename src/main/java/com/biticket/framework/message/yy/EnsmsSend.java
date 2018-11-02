/// HTTPS版加密短信发送DEMO
/// 开发环境 ：JSE1.8,httpclient4.5.2,windows 10 专业版
/// 联系方式 ：346910917@qq.com,18611729367
/// 版本：1.0
/// 最近修订：2017-01-19

package com.biticket.framework.message.yy;

import org.apache.http.client.HttpClient;


public class EnsmsSend {
	private static HttpClient client;

	/**
	 * 加密发送DEMO
	 * @param args
	 */
	/*public static void main(String[] args) {
		final String Url="https://dx.ipyy.net/ensms.ashx";

		//用户ID。
		String userId="";
		
		//用户账号名
		String userName="";
		
		//接口密码
		String password="";
		
		//目标手机号，多个以半角","分隔
		String mobile="18611729367";
		
		//信息内容
		String content="java版调用ensms.ashx接口测试，您的验证码：8888【华信】";
		
		//扩展号，没有请留空
		String ext="";
		
		//即时短信请留空，定时短信请指定，格式为：yyyy-MM-dd HH:mm:ss
		String sendTime="";
		String stamp =new SimpleDateFormat("MMddHHmmss").format(new Date());
		String secret=MD5.GetMD5Code(password+stamp).toUpperCase();
		
		try {
			JSONObject jsonObj=new JSONObject();
			jsonObj.put("UserName", userName);
			jsonObj.put("Stamp", stamp);
			jsonObj.put("Secret", secret);
			jsonObj.put("Moblie", mobile);
			jsonObj.put("Text", content);
			jsonObj.put("Ext", ext);
			jsonObj.put("SendTime", sendTime);
	
			//Des加密，base64转码
			String text64=DesHelper.Encrypt(jsonObj.toString(), password);

			client = new SSLClient();
			HttpPost post=new HttpPost(Url);
			post.setHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("UserId",userId));
			nvps.add(new BasicNameValuePair("Text64",text64));
			post.setEntity(new UrlEncodedFormEntity(nvps));
			HttpResponse response = client.execute(post);
			System.out.println(response.getStatusLine());

			HttpEntity entity = response.getEntity();
			String returnString=EntityUtils.toString(entity, "UTF-8");
			System.out.println(returnString);
			EntityUtils.consume(entity);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}		
	}*/
}
