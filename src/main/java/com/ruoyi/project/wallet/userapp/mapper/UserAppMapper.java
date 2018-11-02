package com.ruoyi.project.wallet.userapp.mapper;

import com.ruoyi.project.wallet.userapp.domain.UserApp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 手机app的apk文件更新记录 数据层
 * 
 * @author ruoyi
 * @date 2018-09-25
 */
public interface UserAppMapper 
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
	 * @return
	 * @param apkType
	 * @param appType
	 */
    UserApp selectUserAppLasterRelease(@Param("apkType")Integer apkType, @Param("appType")Integer appType);
}