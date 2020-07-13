package com.cas.controller.tsController;

import com.cas.owner.service.accountService.AccountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 22:00 2020-07-08
 * @version: V1.0
 * @review:
 */
@Controller
@RequestMapping("/tx")
public class TxController {


    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "事务测试")
    @ResponseBody
    @GetMapping("/addAccount")
    public String addAccount() throws SQLException {
        accountService.insertAccount();
        return "ok";
    }

    @ApiOperation(value = "事务测试")
    @ResponseBody
    @GetMapping("/addAccount2")
    public String addAccount2() throws SQLException {
        accountService.insertAccount2();
        return "ok";
    }



}
