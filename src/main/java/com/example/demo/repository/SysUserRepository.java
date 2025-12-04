package com.example.demo.repository;

import com.example.demo.dto.SysUserDto;
import com.example.demo.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SysUserRepository extends JpaRepository<SysUser, Integer> {
    @Query("SELECT new com.example.demo.dto.SysUserDto(" +
            "s.id, s.name, s.mobile, s.password, s.role) " +
            "FROM SysUser s " +
            "WHERE s.mobile=:mobile and s.password=:password")
    List<SysUserDto> findByMobileAndPassword(@Param("mobile") String mobile,
                                             @Param("password") String password);
}
