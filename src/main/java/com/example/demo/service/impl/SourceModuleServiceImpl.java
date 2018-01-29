package com.example.demo.service.impl;

import com.example.demo.persistence.SourceModule;
import com.example.demo.repository.SourceModuleRepository;
import com.example.demo.service.SourceModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SourceModuleServiceImpl implements SourceModuleService {

    @Autowired
    private SourceModuleRepository sourceModuleRepository;


    @Override
    public void saveData(){
        SourceModule sm = new SourceModule();
        sm.setSvnURL("https://subversion.cambio.se/PC/Standard/CraftModuleClient/branches/R8.1_CraftModuleClient_Int");
        sourceModuleRepository.save(sm);

    }

}
