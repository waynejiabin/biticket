package com.biticket.project.wallet.userTransSerial.controller;

import java.util.List;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.biticket.project.wallet.userTransSerial.domain.UserTransSerial;
import com.biticket.project.wallet.userTransSerial.service.IUserTransSerialService;
import com.biticket.framework.web.controller.BaseController;
import com.biticket.framework.web.page.TableDataInfo;

/**
 * 交易流水 信息操作处理
 *
 * @author biticket
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
