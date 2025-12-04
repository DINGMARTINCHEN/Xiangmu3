package com.example.demo.dto;

public class SysUserDto {
    private int id;
    private String name;
    private String mobile;
    private String password;
    private int role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public SysUserDto() {
    }

    public SysUserDto(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

    public SysUserDto(int id, String name, String mobile, String password, int role) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.password = password;
        this.role = role;
    }
}
