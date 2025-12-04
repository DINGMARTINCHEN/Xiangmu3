package com.example.demo.service.impl;

import com.example.demo.dto.SysUserDto;
import com.example.demo.repository.SysUserRepository;
import com.example.demo.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    SysUserRepository sysUserRepository;

    @Override
    public int login(SysUserDto sysUserDto) {
        List<SysUserDto> sysUserList = sysUserRepository.findByMobileAndPassword(
                sysUserDto.getMobile(), sysUserDto.getPassword()
        );
        if (sysUserList.size() == 0) {
            return 0;
        } else {
            SysUserDto resultDto = sysUserList.get(0);
            return resultDto.getRole();
        }
    }
}
