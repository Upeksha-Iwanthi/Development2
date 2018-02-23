package com.example.demo.service.impl;

import com.example.demo.persistence.FunctionalArea;
import com.example.demo.persistence.ProductArea;
import com.example.demo.repository.FunctionalAreaRepository;
import com.example.demo.repository.ProductAreaRepository;
import com.example.demo.service.JiraService;
import com.example.demo.service.ProductAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductAreaServiceImpl implements ProductAreaService{

    @Autowired
    ProductAreaRepository productAreaRepository;

    @Autowired
    private JiraService jiraService;

    @Autowired
    FunctionalAreaRepository functionalAreaRepository;

    @Override
    public ProductArea findProductAreaForIssueId(String issueId, Map<String,ProductArea> issueProductAreaMap) throws Exception {
        // find product areas and functional area
        ProductArea productArea = issueProductAreaMap.get(issueId);
        if(productArea == null)
        {
            productArea = jiraService.findProductAreasForIssueId(issueId);
            issueProductAreaMap.put(issueId, productArea);
        }

        return productArea;

    }

    @Override
    public ProductArea getProductAreaToSave(ProductArea productArea, List<ProductArea> area, List<FunctionalArea> faList){
        ProductArea productAreaDB = null;
        List<FunctionalArea> faDBList = null;

        if(!area.isEmpty()) {
            productAreaDB = area.get(0);
            for (FunctionalArea fa : productArea.getFa())
            {
                faDBList = functionalAreaRepository.findByName(fa.getName());
                if (!(faDBList.isEmpty()))
                {

                }else {
                    fa.setProductArea(productAreaDB);
                    productAreaDB.getFa().add(fa);
                }
            }
        }else {
            productAreaDB = productArea;
        }

        return productAreaDB;
    }
}
