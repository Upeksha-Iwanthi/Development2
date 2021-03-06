package com.example.demo.service.impl;

import com.example.demo.persistence.FunctionalArea;
import com.example.demo.persistence.FunctionalAreaClass;
import com.example.demo.persistence.ModuleClass;
import com.example.demo.repository.FunctionalAreaClassRepository;
import com.example.demo.repository.FunctionalAreaRepository;
import com.example.demo.repository.ModuleClassRepository;
import com.example.demo.service.FunctionalAreaClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionalAreaClassServiceImpl implements FunctionalAreaClassService{

    @Autowired
    ModuleClassRepository moduleClassRepository;

    @Autowired
    FunctionalAreaClassRepository functionalAreaClassRepository;

    @Autowired
    FunctionalAreaRepository functionalAreaRepository;

    @Override
    public void saveData(){
        List<FunctionalArea> functionalAreas = functionalAreaRepository.findByName("fa3");
        List<ModuleClass> moduleClasses =moduleClassRepository.findByModule("module1");
        FunctionalAreaClass functionalAreaClass1 = new FunctionalAreaClass("100",functionalAreas.get(0));

        functionalAreaClass1.setModuleClass(moduleClasses.get(0));
//        functionalAreaClassRepository.save(functionalAreaClass1);
    }
}
