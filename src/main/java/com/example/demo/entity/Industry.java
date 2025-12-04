package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="industry")
public class Industry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "industry", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Salary> salaryList = new ArrayList<>();

    public Industry() {
    }

    public Industry(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Industry(String name) {
        this.name = name;
    }

    public Industry(int id, String name, List<Salary> salaryList) {
        this.id = id;
        this.name = name;
        this.salaryList = salaryList;
    }
}