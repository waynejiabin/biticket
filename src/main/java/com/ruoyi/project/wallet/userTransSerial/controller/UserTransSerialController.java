package com.ruoyi.project.wallet.userTransSerial.controller;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.project.wallet.member.domain.Member;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.wallet.userTransSerial.domain.UserTransSerial;
import com.ruoyi.project.wallet.userTransSerial.service.IUserTransSerialService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;

import javax.transaction.Transactional;

/**
 * 交易流水 信息操作处理
 *
 * @author ruoyi
 * @date 2018-08-24
 */
@Controller
@RequestMapping("/wallet/userTransSerial")
public class UserTransSerialController extends BaseController {
    private String prefix = "wallet/userTransSerial";

    @Autowired
    private IUserTransSerialService userTransSerialService;


    @GetMapping()
    public String userTransSerial() {
        return prefix + "/userTransSerial";
    }

    /**
     * 查询交易流水列表
     */
    @ApiOperation("查询交易流水")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UserTransSerial userTransSerial) {
        startPage();
        List<UserTransSerial> list = userTransSerialService.selectUserTransSerialList(userTransSerial);
        return getDataTable(list);
    }




}
