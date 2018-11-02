package com.ruoyi.project.wallet.smsValidate.service;

import com.ruoyi.project.wallet.smsValidate.domain.SmsValidate;

import java.util.List;

/**
 * 系统短信发送 服务层
 * 
 * @author ruoyi
 * @date 2018-08-30
 */
public interface ISmsValidateService 
{
	/**
     * 查询系统短信发送信息
     * 
     * @param smsId 系统短信发送ID
     * @return 系统短信发送信息
     */
	SmsValidate selectSmsValidateById(Long smsId);

	/**
	 * 获取验证码
	 * @param smsValidate
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
     * 删除系统短信发送信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	 int deleteSmsValidateByIds(String ids);
	
}
