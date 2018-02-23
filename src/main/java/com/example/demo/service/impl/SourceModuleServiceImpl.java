package com.example.demo.service.impl;

import com.example.demo.persistence.SourceModule;
import com.example.demo.persistence.TargetModule;
import com.example.demo.repository.SourceModuleRepository;
import com.example.demo.repository.TargetModuleRepository;
import com.example.demo.service.SourceModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourceModuleServiceImpl implements SourceModuleService {

    @Autowired
    private TargetModuleRepository targetModuleRepository;

    @Autowired
    private SourceModuleRepository sourceModuleRepository;

    public void saveData(){
        Iterable<TargetModule> targetModules = targetModuleRepository.findAll();
        for (TargetModule targetModule : targetModules)
        {
            SourceModule sm = new SourceModule();
            sm.setSvnURL(targetModule.getSvnURL().replaceAll("_Int","_Dev"));
            sourceModuleRepository.save(sm);

        }
    }
}
