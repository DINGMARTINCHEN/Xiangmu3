package com.example.demo.controller;

import com.example.demo.service.LimitService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;

@Controller
public class HelloController {
    @Resource
    LimitService limitService;

    private final RestTemplate restTemplate = new RestTemplate();

    @RequestMapping("/hello")
    public String helloWorld(Model m) {
        return "hello/index";
    }

    @RequestMapping("/helloJson")
    @ResponseBody
    public String helloJson(){
        return "你好";
    }

    @RequestMapping("/testPython")
    public String testPython(Model m) {
        int type = 1;
        int javaResult = limitService.getLimit(type);
        m.addAttribute("javaResult", javaResult);

        return "python/test";
    }

    @RequestMapping("/testSubmit")
    public String testSubmit(Model m) {
        return "python/inputTest";
    }


    @RequestMapping("/submit")
    public String submitFrom(
            @RequestParam("inputTest") String inputTest,
            Model m) {
        int type = 1;
        if(inputTest != null && !inputTest.isEmpty()) {
            type = Integer.parseInt(inputTest);
        }

        int javaResult = limitService.getLimit(type);
        m.addAttribute("javaResult", javaResult);

        return "python/inputTest";
    }
}
