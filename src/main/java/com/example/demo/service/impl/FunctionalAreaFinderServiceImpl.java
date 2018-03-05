package com.example.demo.service.impl;

import com.example.demo.Data.IssueSearchResult;
import com.example.demo.persistence.FunctionalAreaClass;
import com.example.demo.persistence.IssueId;
import com.example.demo.repository.FunctionalAreaClassRepository;
import com.example.demo.repository.FunctionalAreaRepository;
import com.example.demo.repository.ProductAreaRepository;
import com.example.demo.service.FunctionalAreaFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FunctionalAreaFinderServiceImpl implements FunctionalAreaFinderService {

    @Autowired
    FunctionalAreaClassRepository functionalAreaClassRepository;

    @Autowired
    ProductAreaRepository productAreaRepository;

    @Override
    public List<IssueSearchResult> findFunctionalAreas(String jiraIssueId){
        List<IssueSearchResult> resultList = new ArrayList<>();
        if (jiraIssueId != null && jiraIssueId.length()> 0)
        {
            try {
                String jiraId = jiraIssueId.replaceAll(" ","").toUpperCase();
                final List<FunctionalAreaClass> list = functionalAreaClassRepository.findByJiraIssueId(jiraId);
                for (FunctionalAreaClass faClass : list)
                {
                    IssueSearchResult result = new IssueSearchResult();
                    result.setFunctionalArea(faClass.getFunctionalArea().getName());
                    result.setClassPath(faClass.getModuleClass().getClassPath());
                    result.setModule(faClass.getModuleClass().getModule());
                    result.setJiraIssueIds(getIssueList(faClass));

                    resultList.add(result);
                }
                return resultList;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return resultList;
    }
    private List<String> getIssueList(FunctionalAreaClass faClass){
        List<IssueId> issueList = faClass.getModuleClass().getIssueList();
        List<String> issueIdList = new ArrayList<>();
        for (IssueId id : issueList){
            issueIdList.add(id.getIssueId());
        }
        return issueIdList;
    }

}
