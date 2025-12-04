package com.example.demo.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name="salary")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float workingAge;
    private float salaryAmount;

    // 关键：添加与 Industry 的多对一关联，字段名必须是 "industry"（与 mappedBy 一致）
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "industry_id") // 数据库 salary 表的外键列名（会自动生成，可自定义）
    private Industry industry; // 字段名必须为 "industry"，匹配 Industry.salaryList 的 mappedBy

    // 构造方法需要补充关联字段的重载（或保留无参构造，用 setter 赋值）
    public Salary() {
    }

    public Salary(int id, float workingAge, float salaryAmount, Industry industry) {
        this.id = id;
        this.workingAge = workingAge;
        this.salaryAmount = salaryAmount;
        this.industry = industry;
    }

    public Salary(float workingAge, float salaryAmount, Industry industry) {
        this.workingAge = workingAge;
        this.salaryAmount = salaryAmount;
        this.industry = industry;
    }

    // 原有构造方法保留（用于不需要关联行业的场景）
    public Salary(int id, float workingAge, float salaryAmount) {
        this.id = id;
        this.workingAge = workingAge;
        this.salaryAmount = salaryAmount;
    }

    public Salary(float workingAge, float salaryAmount) {
        this.workingAge = workingAge;
        this.salaryAmount = salaryAmount;
    }
}