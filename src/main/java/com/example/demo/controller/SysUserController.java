package com.example.demo.controller;

import com.example.demo.constant.LoginConstant;
import com.example.demo.constant.RoleConstant;
import com.example.demo.dto.IndustryDto;
import com.example.demo.dto.SysUserDto;
import com.example.demo.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class SysUserController {
    @Resource
    SysUserService sysUserService;

    @RequestMapping("/login/index")
    public String LoginIndex(Model m){
        m.addAttribute("errorMessage", "");
        return "login/index";
    }

    @RequestMapping("/login/loginAction")
    public String loginAction(
            @RequestParam(value = "mobile") String mobile,
            @RequestParam(value = "password") String password,
            Model m){
        SysUserDto sysUserDto = new SysUserDto(mobile, password);
        int role = sysUserService.login(sysUserDto);
        if (role == RoleConstant.ROLE_HR) {
            return "redirect:/salary/list";
        } else if (role == RoleConstant.ROLE_ADMIN) {
            return "redirect:/industry/list";
        } else {
            m.addAttribute("errorMessage", LoginConstant.LOGIN_ERROR);
            return "login/index";
        }
    }
}
