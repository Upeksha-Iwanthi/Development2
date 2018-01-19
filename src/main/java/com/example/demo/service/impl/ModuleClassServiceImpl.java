package com.example.demo.service.impl;

import com.example.demo.persistance.FunctionalAreaClass;
import com.example.demo.persistance.ModuleClass;
import com.example.demo.repository.FunctionalAreaClassRepository;
import com.example.demo.repository.FunctionalAreaRepository;
import com.example.demo.repository.ModuleClassRepository;
import com.example.demo.service.ModuleClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ModuleClassServiceImpl implements ModuleClassService {

    @Autowired
    ModuleClassRepository moduleClassRepository;

    @Override
    public void saveData(){
        ModuleClass moduleClass1 = new ModuleClass("module1","classPath1");
        ModuleClass moduleClass2 = new ModuleClass("module2","classPath2");
        moduleClassRepository.save(moduleClass1);
        moduleClassRepository.save(moduleClass2);
    }
}
