package com.ruoyi.framework.message;

import com.ruoyi.framework.message.dayu.DayuSmsConfig;
import com.ruoyi.framework.message.dayu.SmsUtil;
import com.ruoyi.framework.message.yy.YySmsUtil;
import com.ruoyi.framework.message.yy.config.YySmsConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IMessageAutoConfiguration {
	private static final Logger log = LoggerFactory.getLogger(IMessageAutoConfiguration.class);
	
	@Bean(name="dayuSmsConfig")
	public DayuSmsConfig dayuSmsConfig() {
		return new DayuSmsConfig();
	}

	@Bean(name="yySmsConfig")
	public YySmsConfig yySmsConfig() {
		return new YySmsConfig();
	}

	@Bean(name="kingo.essageInit")
	public Object init() {
		//SmsUtil.init(dayuSmsConfig());
		YySmsUtil.init(yySmsConfig());
		log.debug("------ 信息发送工具初始化成功-----");
		return null;
	}
	
}
