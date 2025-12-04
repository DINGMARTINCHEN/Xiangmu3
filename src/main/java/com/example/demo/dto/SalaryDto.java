package com.example.demo.dto;

import lombok.Data;

@Data
public class SalaryDto {
    private int id;
    private float workingAge;
    private float salaryAmount;
    private int industryId; // 存储行业ID（用于前端传递、后端接收）
    private String industryName; // 可选：用于展示行业名称

    // 补充构造方法（根据业务场景调整）
    public SalaryDto() {
    }

    public SalaryDto(int id, float workingAge, float salaryAmount, int industryId) {
        this.id = id;
        this.workingAge = workingAge;
        this.salaryAmount = salaryAmount;
        this.industryId = industryId;
    }

    public SalaryDto(float workingAge, float salaryAmount, int industryId) {
        this.workingAge = workingAge;
        this.salaryAmount = salaryAmount;
        this.industryId = industryId;
    }

    // 保留原有构造方法（兼容现有业务）
    public SalaryDto(int id, float workingAge, float salaryAmount) {
        this.id = id;
        this.workingAge = workingAge;
        this.salaryAmount = salaryAmount;
    }

    public SalaryDto(float workingAge, float salaryAmount) {
        this.workingAge = workingAge;
        this.salaryAmount = salaryAmount;
    }

    public SalaryDto(int id) {
        this.id = id;
    }
}