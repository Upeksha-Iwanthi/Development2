package com.example.demo.service.impl;

import com.example.demo.Data.IssueSearchResult;
import com.example.demo.persistence.FunctionalArea;
import com.example.demo.persistence.FunctionalAreaClass;
import com.example.demo.persistence.IssueId;
import com.example.demo.persistence.ModuleClass;
import com.example.demo.repository.*;
import com.example.demo.service.FunctionalAreaFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
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

    @Autowired
    ModuleClassRepository moduleClassRepository;

    @Override
    public List<IssueSearchResult> findFunctionalAreasForIssueId(String jiraIssueId){
        List<IssueSearchResult> resultList = new ArrayList<>();
        if (jiraIssueId != null && jiraIssueId.length()> 0)
        {
            try {
                String jiraId = jiraIssueId.replaceAll(" ","").toUpperCase();
                final List<IssueId> list = issueIdRepository.findByIssueId(jiraId);
                for (IssueId issueId : list)
                {
                    List<FunctionalAreaClass> functionalAreaClassList = issueId.getModules().getFunctionalAreaClasses();
                    for (FunctionalAreaClass faClass:functionalAreaClassList) {
                        IssueSearchResult result = new IssueSearchResult();
                        result.setClassPath(issueId.getModules().getClassPath());
                        result.setModule(issueId.getModules().getModule());
                        result.setFunctionalArea(faClass.getFunctionalArea().getName());

                        List<String> issueList = getIssueList(faClass.getFunctionalArea(),functionalAreaClassList);

                        result.setJiraIssueIds(issueList);
                        result.setPercentage(calculatePercentage(issueList.size(),functionalAreaClassList.size()));

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

    @Override
    public List<IssueSearchResult> findFunctionalAreasByClass(String classPath){
        List<IssueSearchResult> resultList = new ArrayList<>();
        if (classPath != null && classPath.length()> 0) {
            try {
                if(!classPath.endsWith(".java")){
                    classPath = classPath + ".java";
                }
                final ModuleClass moduleClass = moduleClassRepository.findByClassPath(classPath);
                List<FunctionalAreaClass> functionalAreaClassList = moduleClass.getFunctionalAreaClasses();
                for (FunctionalAreaClass faClass:functionalAreaClassList) {
                    IssueSearchResult result = new IssueSearchResult();
                    result.setClassPath(classPath);
                    result.setModule(moduleClass.getModule());
                    result.setFunctionalArea(faClass.getFunctionalArea().getName());

                    List<String> issueList = getIssueList(faClass.getFunctionalArea(), functionalAreaClassList);

                    result.setJiraIssueIds(issueList);
                    result.setPercentage(calculatePercentage(issueList.size(), functionalAreaClassList.size()));

                    resultList.add(result);
                }
                return resultList;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return resultList;
    }

    private List<String> getIssueList(FunctionalArea functionalArea, List<FunctionalAreaClass> functionalAreaClasses){
        List<String> issueIdList = new ArrayList<>();
        for (FunctionalAreaClass faClass : functionalAreaClasses){
            if (faClass.getFunctionalArea()==functionalArea)
            issueIdList.add(faClass.getJiraIssueId());
        }
        return issueIdList;
    }

    private double calculatePercentage(int itemCount, int totalCount)
    {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return Double.parseDouble(decimalFormat.format((double) itemCount * 100 / totalCount));

    }

}
