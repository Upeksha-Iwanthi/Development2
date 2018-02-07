package com.example.demo.service;

import com.example.demo.persistence.FunctionalArea;
import com.example.demo.persistence.FunctionalAreaClass;
import com.example.demo.persistence.ProductArea;

import java.util.List;

public interface JiraService {

    ProductArea findProductAreasForIssueId(String issueId) throws Exception;

//    ProductArea findProductAreaForIssueId(String issueId) throws Exception;

}
