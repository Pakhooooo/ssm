package com.ssm.register.controller;

import com.ssm.common.global.Result;
import com.ssm.register.dto.RegisterDTO;
import com.ssm.register.dto.RegisterListDTO;
import com.ssm.register.po.Register;
import com.ssm.register.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RegisterController {
    
    private RegisterService registerService;

    @Autowired
    public void setRegisterService(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping(value = "/register/add")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('register:add')")
    public Result addRegister(@RequestBody Register register) {
        int flag = registerService.addRegister(register);
        if (flag == 0) {
            Result.error("新增报名信息失败");
        }

        return Result.success("新增报名信息成功");
    }

    @DeleteMapping(value = "/register/{registerId}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('register:delete')")
    public Result deleteRegister(@PathVariable int registerId) {
        int flag = registerService.deleteRegister(registerId);
        if (flag == 0) {
            Result.error("报名信息删除失败");
        }

        return Result.success("报名信息删除成功");
    }

    @PutMapping(value = "/register/update")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('register:update')")
    public Result updateRegister(@RequestBody RegisterDTO Register) {
        int flag = registerService.updateRegister(Register);
        if (flag == 0) {
            Result.error("报名信息修改失败");
        }

        return Result.success("报名信息修改成功");
    }

    @GetMapping(value = "/register/{registerId}")
    public Result getRegister(@PathVariable int registerId) {
        return Result.success(registerService.getRegister(registerId), "报名信息查询成功");
    }

    @PostMapping(value = "/registers")
    public Result getRegisters(@Valid @RequestBody RegisterListDTO registerListDTO) {
        return Result.success(registerService.getRegisters(registerListDTO), "报名信息列表查询成功");
    }
    
}
