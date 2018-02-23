package com.example.demo.persistence;

import com.example.demo.repository.SourceModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SourceModuleDataServiceImpl implements SourceModuleDataService {

    @Autowired
    SourceModuleRepository sourceModuleRepository;

    @Override
    public List<SourceModule> getConfiguredBranchList(String type){
        final List<SourceModule> data = new ArrayList<>();
        for (final SourceModule obj : sourceModuleRepository.findByType(type))
        {
            data.add(obj);
        }
        return data;
    }
}
