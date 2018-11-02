package com.ruoyi.project.wallet.userapp.service;

import com.ruoyi.project.wallet.userapp.domain.UserApp;
import com.ruoyi.project.wallet.userapp.mapper.UserAppMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 手机app的apk文件更新记录 服务层实现
 * 
 * @author ruoyi
 * @date 2018-09-25
 */
@Service
public class UserAppServiceImpl implements IUserAppService
{
	@Autowired
	private UserAppMapper userAppMapper;

	/**
     * 查询手机app的apk文件更新记录信息
     * 
     * @param appId 手机app的apk文件更新记录ID
     * @return 手机app的apk文件更新记录信息
     */
    @Override
	public UserApp selectUserAppById(Integer appId)
	{
	    return userAppMapper.selectUserAppById(appId);
	}
	
	/**
     * 查询手机app的apk文件更新记录列表
     * 
     * @param userApp 手机app的apk文件更新记录信息
     * @return 手机app的apk文件更新记录集合
     */
	@Override
	public List<UserApp> selectUserAppList(UserApp userApp)
	{
	    return userAppMapper.selectUserAppList(userApp);
	}

	/***
	 *
	 * @return
	 * @param apkType
	 * @param appType
	 */
	@Override
	public UserApp selectUserAppLasterRelease(Integer apkType, Integer appType) {
		return  userAppMapper.selectUserAppLasterRelease(apkType,appType);
	}


}
