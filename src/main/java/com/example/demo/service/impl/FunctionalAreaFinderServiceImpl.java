package com.example.demo.service.impl;

import com.example.demo.Data.IssueSearchResult;
import com.example.demo.persistence.FunctionalArea;
import com.example.demo.persistence.FunctionalAreaClass;
import com.example.demo.persistence.IssueId;
import com.example.demo.repository.FunctionalAreaClassRepository;
import com.example.demo.repository.FunctionalAreaRepository;
import com.example.demo.repository.IssueIdRepository;
import com.example.demo.repository.ProductAreaRepository;
import com.example.demo.service.FunctionalAreaFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FunctionalAreaFinderServiceImpl implements FunctionalAreaFinderService {

    @Autowired
    IssueIdRepository issueIdRepository;

    @Autowired
    ProductAreaRepository productAreaRepository;

    @Autowired
    FunctionalAreaClassRepository functionalAreaClassRepository;

    @Override
    public List<IssueSearchResult> findFunctionalAreas(String jiraIssueId){
        List<IssueSearchResult> resultList = new ArrayList<>();
        if (jiraIssueId != null && jiraIssueId.length()> 0)
        {
            try {
                String jiraId = jiraIssueId.replaceAll(" ","").toUpperCase();
                final List<IssueId> list = issueIdRepository.findByIssueId(jiraId);
                for (IssueId issueId : list)
                {
                    for (FunctionalAreaClass faClass:issueId.getModules().getFunctionalAreaClasses()) {
                        IssueSearchResult result = new IssueSearchResult();
                        result.setClassPath(issueId.getModules().getClassPath());
                        result.setModule(issueId.getModules().getModule());
                        result.setFunctionalArea(faClass.getFunctionalArea().getName());
                        result.setJiraIssueIds(getIssueList(faClass.getFunctionalArea()));

                        resultList.add(result);
                    }
                }
                return resultList;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return resultList;
    }
    private List<String> getIssueList(FunctionalArea functionalArea){
        List<FunctionalAreaClass> faClassList = functionalAreaClassRepository.findByFunctionalArea(functionalArea);
        List<String> issueIdList = new ArrayList<>();
        for (FunctionalAreaClass faClass : faClassList){
            issueIdList.add(faClass.getJiraIssueId());
        }
        return issueIdList;
    }

}
