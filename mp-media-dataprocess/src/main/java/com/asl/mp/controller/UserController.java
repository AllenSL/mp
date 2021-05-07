package com.asl.mp.controller;

import com.asl.mp.entity.User;
import com.asl.mp.service.IUserService;
import com.asl.mp.util.ResultInfo;
import com.asl.mp.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ClassName UserController
 * @Description
 * @Author asl
 * @Date 2021/4/27 13:45
 **/
@RestController
public class UserController {

    private final IUserService iUserService;

    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @PostMapping("/save")
    public ResultInfo save(@RequestBody @Valid User user){
        iUserService.save(user);
       return ResultUtil.success();
    }

    @GetMapping("/query")
    public ResultInfo query(@RequestParam String des){
        return ResultUtil.success(iUserService.queryByDes(des));
    }
}
