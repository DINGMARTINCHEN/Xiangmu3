package com.example.demo.service.impl;

import com.example.demo.dto.SalaryDto;
import com.example.demo.entity.Salary;
import com.example.demo.repository.SalaryRepository;
import com.example.demo.service.SalaryService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SalaryServiceImpl implements SalaryService {
    @Resource
    SalaryRepository salaryRepository;

    private final RestTemplate restTemplate = new RestTemplate();
    @Override
    public List<SalaryDto> findAll() {
        return salaryRepository.findAllSalaryDto();
    }

    @Override
    public List<SalaryDto> findByWorkingAge(float workingAge) {
        return salaryRepository.findSalaryDtoByWorkingAge(workingAge);
    }

    @Override
    public void add(SalaryDto entity) {
        Salary salary = new Salary(entity.getWorkingAge(), entity.getSalaryAmount());
        salaryRepository.save(salary);
    }

    @Override
    public void update(SalaryDto entity) {
        Salary salary = find(entity.getId());
        salary.setWorkingAge(entity.getWorkingAge());
        salary.setSalaryAmount(entity.getSalaryAmount());
        salaryRepository.save(salary);
    }

    @Override
    public void delete(SalaryDto entity) {
        Salary salary =find(entity.getId());
        salaryRepository.delete(salary);
    }

    @Override
    public SalaryDto findById(int id) {
        return salaryRepository.findSalaryDtoById(id);
    }

    @Override
    public float predict(float workingAge) {
        String baseUrl = "http://127.0.0.1:5000/SalaryLinearRegressionPredict";
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = "";
        try{
            requestJson = mapper.writeValueAsString(
                    Collections.singletonMap("workingAge", workingAge));
        }catch (Exception e){
            throw new RuntimeException("JSON序列化失败:" + e.getMessage());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers);

        try{
            ResponseEntity<String> response = restTemplate.exchange(
                    baseUrl,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            String jsonResponse = response.getBody();
            JsonNode rootNode = mapper.readTree(jsonResponse);
            String result = rootNode.get("result").toString();
            System.out.println("从Python获取的运行结果：" + result);
            return Float.parseFloat(result);
        }catch (Exception e){
            throw new RuntimeException("调用Flask服务失败" + e.getMessage());
        }
    }

    public Salary find(int id) {
        Optional<Salary> optionalSalary = salaryRepository.findById(id);
        Salary salary = optionalSalary.orElseThrow(
                () -> new EntityNotFoundException(
                        "编号" + id + "对应的Salary类未找到"
                )
        );
        return salary;
    }
}
