package com.example.demo.service;

import com.example.demo.Data.IssueSearchResult;
import com.example.demo.persistence.FunctionalArea;
import com.example.demo.persistence.FunctionalAreaClass;

import java.util.List;

public interface FunctionalAreaFinderService {
    List<IssueSearchResult> findFunctionalAreasForIssueId2(String jiraIssueId);

//    List<IssueSearchResult> findFunctionalAreasForIssueId(String jiraIssueid);
    List<String> getIssueList(FunctionalArea functionalArea, List<FunctionalAreaClass> functionalAreaClasses);
    double calculatePercentage(int itemCount, int totalCount);
}
