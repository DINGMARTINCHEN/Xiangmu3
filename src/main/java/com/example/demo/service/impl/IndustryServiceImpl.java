package com.example.demo.service.impl;

import com.example.demo.dto.IndustryDto;
import com.example.demo.entity.Industry;
import com.example.demo.repository.IndustryRepository;
import com.example.demo.service.IndustryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class IndustryServiceImpl implements IndustryService {
    @Resource
    IndustryRepository industryRepository;
    public List<IndustryDto> findAll() {
        return industryRepository.findAllIndustryDto();
    }

    @Override
    public List<IndustryDto> findByNameLike(String name) {
        return industryRepository.findIndustryDtoNameLike(name);
    }

    @Override
    public void add(IndustryDto entity) {
        Industry industry = new Industry(entity.getName());
        industryRepository.save(industry);
    }

    @Override
    public void delete(IndustryDto entity) {
        Industry industry = find(entity.getId());
        industryRepository.delete(industry);
    }

    @Override
    public void update(IndustryDto entity) {
        Industry industry = find(entity.getId());
        industry.setName(entity.getName());
        industryRepository.save(industry);
    }

    @Override
    public IndustryDto findById(int id) {
        return industryRepository.findIndustryDtoById(id);
    }
    public Industry find(int id){
        Optional<Industry> optionalIndustry = industryRepository.findById(id);
        Industry industry = optionalIndustry.orElseThrow(
                () -> new EntityNotFoundException(
                        "编号" + id + "对应的Industry类未找到"
                )
        );
        return industry;
    }


}