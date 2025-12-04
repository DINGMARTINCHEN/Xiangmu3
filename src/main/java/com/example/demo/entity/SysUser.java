package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sysuser")
public class SysUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String mobile;
    private String password;
    private int role;

    public SysUser() {
    }

    public SysUser(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

    public SysUser(int id, String name, String mobile, String password, int role) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.password = password;
        this.role = role;
    }
}
