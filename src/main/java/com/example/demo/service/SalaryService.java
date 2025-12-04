package com.example.demo.service;

import com.example.demo.dto.SalaryDto;

import java.util.List;

public interface SalaryService {
    List<SalaryDto> findAll();
    List<SalaryDto> findByWorkingAge(float workingAge);
    void add(SalaryDto entity);
    void update(SalaryDto entity);
    void delete(SalaryDto entity);
    SalaryDto findById(int id);
    float predict(float workingAge);
}
