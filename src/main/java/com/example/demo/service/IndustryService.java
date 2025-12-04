package com.example.demo.service;

import com.example.demo.dto.IndustryDto;

import java.util.List;

public interface IndustryService {
    List<IndustryDto> findAll();
    List<IndustryDto> findByNameLike(String name);
    void add(IndustryDto entity);
    void delete(IndustryDto entity);
    void update(IndustryDto entity);
    IndustryDto findById(int id);
}