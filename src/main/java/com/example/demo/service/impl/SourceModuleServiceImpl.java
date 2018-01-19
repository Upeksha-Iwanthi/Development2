package com.example.demo.service.impl;

import com.example.demo.persistance.SourceModule;
import com.example.demo.repository.SourceModuleRepository;
import com.example.demo.service.SourceModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SourceModuleServiceImpl implements SourceModuleService {

    @Autowired
    private SourceModuleRepository sourceModuleRepository;


    @Override
    public void saveData(SourceModule sourceModule){
        sourceModuleRepository.save(sourceModule);

    }

}
