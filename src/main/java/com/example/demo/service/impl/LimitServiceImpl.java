package com.example.demo.service.impl;

import com.example.demo.entity.LimitObject;
import com.example.demo.repository.LimitObjectRepository;
import com.example.demo.service.LimitService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;

@Service
public class LimitServiceImpl implements LimitService {
    @Resource
    LimitObjectRepository limitObjectRepository;

    private final RestTemplate restTemplate = new RestTemplate();
    @Override
    public int getLimit(int type) {
        String baseUrl = "http://127.0.0.1:5000/limitTest/";

        try{
            String url = UriComponentsBuilder.fromHttpUrl(baseUrl).pathSegment(String.valueOf(type)).toUriString();

            String jsonResponse = restTemplate.getForObject(url, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonResponse);

            System.out.println("从Python取得的运行结果：" + rootNode.get("result"));

            int result = rootNode.get("result").asInt();
            save(type, result);
            return result;
        }catch (Exception e) {
            throw new RuntimeException("调用Flask服务失败：" + e.getMessage());
        }
    }

    public void save(int limitType, int limitResult){
        LimitObject limitObject = new LimitObject();
        limitObject.setLimitType(limitType);
        limitObject.setLimitResult(limitResult);
        limitObjectRepository.save(limitObject);
    }
}
