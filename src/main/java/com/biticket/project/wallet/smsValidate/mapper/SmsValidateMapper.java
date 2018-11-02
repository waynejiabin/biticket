package com.biticket.project.wallet.smsValidate.mapper;

import com.biticket.project.wallet.smsValidate.domain.SmsValidate;
import java.util.List;	

/**
 * 系统短信发送 数据层
 * 
 * @author biticket
 * @date 2018-08-30
 */
public interface SmsValidateMapper 
{
	/**
     * 查询系统短信发送信息
     * 
     * @param smsId 系统短信发送ID
     * @return 系统短信发送信息
     */
	 SmsValidate selectSmsValidateById(Long smsId);

	/**
	 * 根据手机号码获取验证码
	 * @param mobile
	 * @param type
     * @return
	 */
	SmsValidate selectSmsValidateByMobile(SmsValidate smsValidate);
	/**
     * 查询系统短信发送列表
     * 
     * @param smsValidate 系统短信发送信息
     * @return 系统短信发送集合
     */
	 List<SmsValidate> selectSmsValidateList(SmsValidate smsValidate);
	
	/**
     * 新增系统短信发送
     * 
     * @param smsValidate 系统短信发送信息
     * @return 结果
     */
	 int insertSmsValidate(SmsValidate smsValidate);
	
	/**
     * 修改系统短信发送
     * 
     * @param smsValidate 系统短信发送信息
     * @return 结果
     */
	 int updateSmsValidate(SmsValidate smsValidate);
	
	/**
     * 删除系统短信发送
     * 
     * @param smsId 系统短信发送ID
     * @return 结果
     */
	 int deleteSmsValidateById(Long smsId);
	
	/**
     * 批量删除系统短信发送
     * 
     * @param smsIds 需要删除的数据ID
     * @return 结果
     */
	 int deleteSmsValidateByIds(String[] smsIds);
	
}