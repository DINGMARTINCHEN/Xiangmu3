package com.example.demo.repository;


import com.example.demo.dto.IndustryDto;

import com.example.demo.entity.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IndustryRepository extends JpaRepository<Industry, Integer> {
    @Query("SELECT new com.example.demo.dto.IndustryDto(" +
            "i.id, i.name) " +
            "FROM Industry i")
    List<IndustryDto> findAllIndustryDto();

    @Query("SELECT new com.example.demo.dto.IndustryDto(" +
            "i.id, i.name) " +
            "From Industry i " +
            "WHERE i.id=:id")
    IndustryDto findIndustryDtoById(@Param("id") int id);

    @Query("SELECT new com.example.demo.dto.IndustryDto(" +
            "i.id, i.name) " +
            "From Industry i " +
            "WHERE i.name like %:name%")
    List<IndustryDto> findIndustryDtoNameLike(@Param("name") String name);
}