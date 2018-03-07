package com.example.demo.service.impl;

import com.example.demo.Data.IssueSearchResult;
import com.example.demo.persistence.FunctionalAreaClass;
import com.example.demo.persistence.ModuleClass;
import com.example.demo.repository.ModuleClassRepository;
import com.example.demo.service.FindFunctionalAreaByClassService;
import com.example.demo.service.FunctionalAreaFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindFunctionalAreaByClassServiceImpl implements FindFunctionalAreaByClassService {

    @Autowired
    ModuleClassRepository moduleClassRepository;

    @Autowired
    FunctionalAreaFinderService functionalAreaFinderService;

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
                for (FunctionalAreaClass faClass:functionalAreaClassList)
                {
                    IssueSearchResult result = new IssueSearchResult();
                    result.setClassPath(classPath);
                    result.setModule(moduleClass.getModule());
                    result.setFunctionalArea(faClass.getFunctionalArea().getName());
                    result.setProductArea(faClass.getFunctionalArea().getProductArea().getName());

                    List<String> issueList = functionalAreaFinderService.getIssueList(faClass.getFunctionalArea(), functionalAreaClassList);

                    result.setJiraIssueIds(issueList);
                    result.setPercentage(functionalAreaFinderService.calculatePercentage(issueList.size(), functionalAreaClassList.size()));

                    resultList.add(result);
                }
                return resultList;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return resultList;
    }
}
