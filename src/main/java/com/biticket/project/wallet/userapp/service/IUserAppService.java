package com.biticket.project.wallet.userapp.service;

import com.biticket.project.wallet.userapp.domain.UserApp;

import java.util.List;

/**
 * 手机app的apk文件更新记录 服务层
 * 
 * @author biticket
 * @date 2018-09-25
 */
public interface IUserAppService 
{
	/**
     * 查询手机app的apk文件更新记录信息
     * 
     * @param appId 手机app的apk文件更新记录ID
     * @return 手机app的apk文件更新记录信息
     */
	UserApp selectUserAppById(Integer appId);
	
	/**
     * 查询手机app的apk文件更新记录列表
     * 
     * @param userApp 手机app的apk文件更新记录信息
     * @return 手机app的apk文件更新记录集合
     */
	 List<UserApp> selectUserAppList(UserApp userApp);


	 /**
	  * 获取最新版本
	  *
	  * @param apkType
	  * @param appType*/
    UserApp selectUserAppLasterRelease(Integer apkType, Integer appType);
}
