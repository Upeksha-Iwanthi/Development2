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
import java.util.HashMap;
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
        public List<IssueSearchResult> findFunctionalAreasForIssueId2(String jiraIssueId) {

            List<IssueSearchResult> resultList = new ArrayList<>();
//          remove spaces in jiraIssueId and set to uppercase.
            String jiraId = jiraIssueId.replaceAll(" ","").toUpperCase();
            final List<IssueId> issueId = issueIdRepository.findByIssueId(jiraId);
            final List<ModuleClass> modifiedClasses = moduleClassRepository.findByIssueListIn(issueId);
            for (ModuleClass moduleClass: modifiedClasses){
                String module = moduleClass.getModule();
                String classPath = moduleClass.getClassPath();
                List<FunctionalAreaClass> functionalAreaClasses = moduleClass.getFunctionalAreaClasses();
                HashMap<String,List<String>> functionalAreaMap = getFunctionalAreasAndIssueIdList(functionalAreaClasses);

                for (HashMap.Entry<String,List<String>> entry : functionalAreaMap.entrySet()){
                    IssueSearchResult result = new IssueSearchResult(classPath,module,entry.getKey());
                    String issueList = "";
                    for (String s:entry.getValue()){
                        issueList = issueList.concat(s+",");
                    }
                    issueList = issueList+"\b";

                    double percentage = calculatePercentage(entry.getValue().size(),functionalAreaMap.keySet().size());
                    result.setPercentage(percentage);
                    result.setIssueList(issueList);
                    resultList.add(result);
                }

            }
            return resultList;
        }

        private HashMap<String,List<String>> getFunctionalAreasAndIssueIdList(List<FunctionalAreaClass> functionalAreaClasses){
            HashMap<String,List<String>> functionalAreaMap = new HashMap<>();
            for (FunctionalAreaClass faClass:functionalAreaClasses)
            {
                if (faClass.getFunctionalArea() == null){
                    continue;
                }
                String functionalArea = faClass.getFunctionalArea().getName();
                String issue_Id = faClass.getJiraIssueId();

                if (functionalAreaMap.containsKey(functionalArea)){
                    functionalAreaMap.get(functionalArea).add(issue_Id);
                }else {
                    List<String> issueList = new ArrayList<>();
                    issueList.add(issue_Id);
                    functionalAreaMap.put(functionalArea,issueList);
                }

            }
            return functionalAreaMap;
        }



    @Override
    public List<String> getIssueList(FunctionalArea functionalArea, List<FunctionalAreaClass> functionalAreaClasses){
        List<String> issueIdList = new ArrayList<>();
        for (FunctionalAreaClass faClass : functionalAreaClasses){
            if (faClass.getFunctionalArea()==functionalArea)
            issueIdList.add(faClass.getJiraIssueId());
        }
        return issueIdList;
    }

    @Override
    public double calculatePercentage(int itemCount, int totalCount)
    {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return Double.parseDouble(decimalFormat.format((double) itemCount * 100 / totalCount));

    }

}
