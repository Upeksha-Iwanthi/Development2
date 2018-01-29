package com.example.demo.service.impl;

import com.example.demo.persistence.FunctionalArea;
import com.example.demo.persistence.ProductArea;
import com.example.demo.repository.FunctionalAreaRepository;
import com.example.demo.repository.ProductAreaRepository;
import com.example.demo.service.FunctionalAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionalAreaServiceImpl implements FunctionalAreaService {

    @Autowired
    FunctionalAreaRepository functionalAreaRepository;

    @Autowired
    ProductAreaRepository productAreaRepository;

    @Override
    public void saveData(){
        List<ProductArea> theatreManagement = productAreaRepository.findByName("Theatre Management");
        List<ProductArea> other = productAreaRepository.findByName("Other");

        FunctionalArea fa1 = new FunctionalArea("fa1",theatreManagement.get(0));
        FunctionalArea fa2 = new FunctionalArea("fa2",other.get(0));

        functionalAreaRepository.save(fa1);
        functionalAreaRepository.save(fa2);

    }
}
