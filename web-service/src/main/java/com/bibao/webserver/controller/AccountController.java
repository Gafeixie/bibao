package com.bibao.webserver.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bibao.webserver.Result;
import com.bibao.webserver.mapper.AccountMapper;
import com.bibao.webserver.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2021-06-20
 */
@RestController
public class AccountController {
    @Autowired
    AccountMapper accountMapper;

    @GetMapping("/login")
    public Result login(@RequestParam("phone") String phone,
                        @RequestParam("password") String password) {
        try {
            if (accountMapper.selectOne(new QueryWrapper<Account>().eq("phone", phone)).getPassword().equals(password)) {
                return Result.ok();
            } else {
                return Result.error("登录错误");
            }
        } catch (Exception e) {
            return Result.error("登录错误");
        }
    }

    @GetMapping("/sign")
    public Result sign(@RequestParam("phone") String phone,
                       @RequestParam("password") String password,
                       @RequestParam("name") String name){
        try{
            Account account = new Account();
            account.setName(name);
            account.setPhone(phone);
            account.setPassword(password);
            accountMapper.insert(account);
            return Result.ok();
        }catch (Exception e){
            return Result.error("注册失败");
        }
    }
}
