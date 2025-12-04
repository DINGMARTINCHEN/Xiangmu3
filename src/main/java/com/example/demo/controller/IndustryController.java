package com.example.demo.controller;

import com.example.demo.dto.IndustryDto;
import com.example.demo.service.IndustryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndustryController {
    @Resource
    IndustryService industryService;

    @RequestMapping("/industry/list")
    public String industryList(Model m){
        List<IndustryDto> industryList = industryService.findAll();
        m.addAttribute("industryList", industryList);
        return "industry/list";
    }

    @RequestMapping("/industry/getBy")
    public String industryGetBy(
            @RequestParam(value = "name") String name,
            Model m){
        List<IndustryDto> industryList = new ArrayList<>();
        if (name.isEmpty() || name.equals("")){
            industryList = industryService.findAll();
        }
        else {
            industryList = industryService.findByNameLike(name);
        }
        m.addAttribute("industryList", industryList);
        return "industry/list";
    }

    @RequestMapping("/industry/create")
    public String industryCreate(Model m){
        return "industry/create";
    }

    @RequestMapping("/industry/createAction")
    public String industryCreateAction(
            @RequestParam(value = "name") String name,
            Model m)
    {
        IndustryDto industryDto = new IndustryDto(name);
        industryService.add(industryDto);

        List<IndustryDto> industryList = industryService.findAll();
        m.addAttribute("industryList", industryList);
        return "industry/list";
    }

    @RequestMapping("/industry/edit")
    public String industryEdit(
            @RequestParam int id,
            Model m){
        IndustryDto industryDto = industryService.findById(id);
        m.addAttribute("industry",industryDto);
        return "industry/edit";
    }

    @RequestMapping("/industry/editAction")
    public String industryEditAction(
            @RequestParam("id") Integer id,
            @RequestParam(value = "name") String name,
            Model m) {
        IndustryDto industryDto = new IndustryDto(id,name);
        industryService.update(industryDto);
        List<IndustryDto> industryList = industryService.findAll();
        m.addAttribute("industryList", industryList);
        return "industry/list";
    }

    @RequestMapping("/industry/deleteAction")
    public String industryDeleteAction(@RequestParam("id") Integer id, Model m) {
        try {

            IndustryDto industrydto = new IndustryDto(id);
            industryService.delete(industrydto);
            m.addAttribute("successMsg", "删除成功！");
        } catch (EntityNotFoundException e) {
            m.addAttribute("errorMsg", e.getMessage());
        }
        return "redirect:/industry/list";
    }
}