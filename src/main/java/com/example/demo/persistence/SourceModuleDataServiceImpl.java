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
    public List<SourceModule> getConfiguredBranchList(){
        final List<SourceModule> data = new ArrayList<SourceModule>();
        for (final SourceModule obj : sourceModuleRepository.findAll())
        {
            data.add(obj);
        }
        return data;
    }

}
