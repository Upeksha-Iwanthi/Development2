package com.example.demo.service;

import com.example.demo.persistence.FunctionalArea;
import com.example.demo.persistence.ProductArea;

import java.util.List;
import java.util.Map;

public interface ProductAreaService {
    ProductArea findProductAreaForIssueId(String issueId, Map<String,ProductArea> issueProductAreaMap) throws Exception;
    ProductArea getProductAreaToSave(ProductArea productArea, List<ProductArea> area, List<FunctionalArea> faList);
}
