package com.ruoyi.project.wallet.smsValidate.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.wallet.smsValidate.mapper.SmsValidateMapper;
import com.ruoyi.project.wallet.smsValidate.domain.SmsValidate;
import com.ruoyi.common.support.Convert;

/**
 * 系统短信发送 服务层实现
 * 
 * @author kingo
 * @date 2018-08-30
 */
@Service
public class SmsValidateServiceImpl implements ISmsValidateService 
{
	@Autowired
	private SmsValidateMapper smsValidateMapper;

	/**
     * 查询系统短信发送信息
     * 
     * @param smsId 系统短信发送ID
     * @return 系统短信发送信息
     */
    @Override
	public SmsValidate selectSmsValidateById(Long smsId)
	{
	    return smsValidateMapper.selectSmsValidateById(smsId);
	}

	/**
	 * 根据手机号码获取验证码
	 * @param smsValidate
     * @return
	 */
	@Override
	public SmsValidate selectSmsValidateByMobile(SmsValidate smsValidate) {
		return smsValidateMapper.selectSmsValidateByMobile(smsValidate);
	}

	/**
     * 查询系统短信发送列表
     * 
     * @param smsValidate 系统短信发送信息
     * @return 系统短信发送集合
     */
	@Override
	public List<SmsValidate> selectSmsValidateList(SmsValidate smsValidate)
	{
	    return smsValidateMapper.selectSmsValidateList(smsValidate);
	}
	
    /**
     * 新增系统短信发送
     * 
     * @param smsValidate 系统短信发送信息
     * @return 结果
     */
	@Override
	public int insertSmsValidate(SmsValidate smsValidate)
	{
		/*smsValidate.setCreateTime(new Date());
		smsValidate.setExpireTime( new Date(new Date().getTime() + 600000));*/
		return smsValidateMapper.insertSmsValidate(smsValidate);
	}
	
	/**
     * 修改系统短信发送
     * 
     * @param smsValidate 系统短信发送信息
     * @return 结果
     */
	@Override
	public int updateSmsValidate(SmsValidate smsValidate)
	{
	    return smsValidateMapper.updateSmsValidate(smsValidate);
	}

	/**
     * 删除系统短信发送对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSmsValidateByIds(String ids)
	{
		return smsValidateMapper.deleteSmsValidateByIds(Convert.toStrArray(ids));
	}
	
}
