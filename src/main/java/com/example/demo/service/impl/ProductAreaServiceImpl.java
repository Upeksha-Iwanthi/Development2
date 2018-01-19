package com.example.demo.service.impl;

import com.example.demo.persistance.ProductArea;
import com.example.demo.repository.ProductAreaRepository;
import com.example.demo.service.ProductAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductAreaServiceImpl implements ProductAreaService{

    @Autowired
    ProductAreaRepository productAreaRepository;

    @Override
    public void saveData(){
        ProductArea theatreManagement = new ProductArea("Theatre Management");
        ProductArea other = new ProductArea("Other");

        productAreaRepository.save(theatreManagement);
        productAreaRepository.save(other);
    }
}
