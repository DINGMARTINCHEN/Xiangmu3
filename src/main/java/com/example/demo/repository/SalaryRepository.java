package com.example.demo.repository;

import com.example.demo.dto.SalaryDto;
import com.example.demo.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalaryRepository extends JpaRepository<Salary, Integer> {
    @Query("SELECT new com.example.demo.dto.SalaryDto(" +
            "s.id, s.workingAge, s.salaryAmount) " +
            "FROM Salary s")
    List<SalaryDto> findAllSalaryDto();

    @Query("SELECT new com.example.demo.dto.SalaryDto(" +
            "s.id, s.workingAge, s.salaryAmount) " +
            "FROM Salary s " +
            "WHERE ABS(s.workingAge - :workingAge) < 0.0001")
    List<SalaryDto> findSalaryDtoByWorkingAge(@Param("workingAge") float workingAge);

    @Query("SELECT new com.example.demo.dto.SalaryDto(" +
            "s.id, s.workingAge, s.salaryAmount) " +
            "FROM Salary s " +
            "WHERE s.id=:id")
    SalaryDto findSalaryDtoById(@Param("id") int id);
}
