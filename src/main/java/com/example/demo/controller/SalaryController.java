package com.example.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.example.demo.dto.SalaryDto;
import com.example.demo.service.SalaryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SalaryController {
    @Resource
    SalaryService salaryService;

    @RequestMapping("/salary/list")
    public String salaryList(Model m){
        List<SalaryDto> salaryList = salaryService.findAll();
        m.addAttribute("salaryList", salaryList);
        return "salary/list";
    }

    @RequestMapping("/salary/getBy")
    public String salaryGetBy(
            @RequestParam(value = "workingAge", required = false) Float workingAge,
            Model m) {
        List<SalaryDto> salaryList = new ArrayList<>();
        if(workingAge == null){
            salaryList = salaryService.findAll();
        } else {
            salaryList = salaryService.findByWorkingAge(workingAge);
        }
        m.addAttribute("salaryList", salaryList);
        return "salary/list";
    }

    @RequestMapping("/salary/create")
    public String salaryCreate(Model m) {
        return "salary/create";
    }

    @RequestMapping("/salary/createAction")
    public String salaryCreateAction(
            @RequestParam("workingAge") Float workingAge,
            @RequestParam("salaryAmount") Float salaryAmount,
            Model m) {
        SalaryDto salaryDto = new SalaryDto(workingAge, salaryAmount);
        salaryService.add(salaryDto);
        List<SalaryDto> salaryList = salaryService.findAll();
        m.addAttribute("salaryList", salaryList);
        return "salary/list";
    }

    @RequestMapping("/salary/edit")
    public String salaryEdit(
            @RequestParam int id,
            Model m) {
        SalaryDto salaryDto = salaryService.findById(id);
        m.addAttribute("salary", salaryDto);
        return "salary/edit";
    }

    @RequestMapping("/salary/editAction")
    public String salaryEditAction(
            @RequestParam("id") Integer id,
            @RequestParam("workingAge") Float workingAge,
            @RequestParam("salaryAmount") Float salaryAmount,
            Model m) {
        SalaryDto salaryDto = new SalaryDto(id, workingAge, salaryAmount);
        salaryService.update(salaryDto);
        List<SalaryDto> salaryList = salaryService.findAll();
        m.addAttribute("salaryList", salaryList);
        return "salary/list";
    }

    @RequestMapping("/salary/deleteAction")
    public String salaryDeleteAction(
            @RequestParam("id") Integer id,
            Model m) {
        SalaryDto salaryDto = new SalaryDto(id);
        salaryService.delete(salaryDto);
        List<SalaryDto> salaryList = salaryService.findAll();
        m.addAttribute("salaryList", salaryList);
        return "salary/list";
    }

    @RequestMapping("/salary/export")
    public void salaryExport(HttpServletResponse response) throws IOException {
        List<SalaryDto> salaryList = salaryService.findAll();

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-disposition", "attachment;filename=salary.xlsx");

        EasyExcel.write(response.getOutputStream(), SalaryDto.class)
                .sheet("薪资数据")
                .doWrite(salaryList);
    }

    @RequestMapping("/salary/predict")
    public String salaryPredict(Model m) {
        return "salary/predict";
    }

    @RequestMapping("/salary/predictAction")
    public String salaryPredictAction(
            @RequestParam("workingAge") Float workingAge,
            Model m) {
        float predictSalary = salaryService.predict(workingAge);
        m.addAttribute("predictSalary", predictSalary);
        return "salary/predict";
    }
}
