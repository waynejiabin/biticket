package com.biticket.project.wallet.userapp.controller;

import com.biticket.framework.web.controller.BaseController;
import com.biticket.framework.web.page.TableDataInfo;
import com.biticket.project.wallet.userapp.domain.UserApp;
import com.biticket.project.wallet.userapp.service.IUserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 手机app的apk文件更新记录 信息操作处理
 * 
 * @author biticket
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
