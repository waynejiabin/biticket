package com.ruoyi.project.wallet.userapp.controller;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.wallet.userapp.domain.UserApp;
import com.ruoyi.project.wallet.userapp.service.IUserAppService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 手机app的apk文件更新记录 信息操作处理
 * 
 * @author ruoyi
 * @date 2018-09-25
 */
@Controller
@RequestMapping("/wallet/userapp")
public class UserAppController extends BaseController
{
    private String prefix = "wallet/userapp";
	
	@Autowired
	private IUserAppService userAppService;

	/**
	 * 查询手机app的apk文件更新记录列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(UserApp userApp)
	{
		startPage();
        List<UserApp> list = userAppService.selectUserAppList(userApp);
		return getDataTable(list);
	}
	





}
