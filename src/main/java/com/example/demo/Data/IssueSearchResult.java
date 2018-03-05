package com.example.demo.Data;

import com.example.demo.persistence.FunctionalArea;

import java.util.List;

public class IssueSearchResult {
    private String classPath;
    private String module;
    private String functionalArea;
    private List<String> jiraIssueIds;

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getFunctionalAreas() {
        return functionalArea;
    }

    public void setFunctionalArea(String functionalArea) {
        this.functionalArea = functionalArea;
    }

    public List<String> getJiraIssueIds() {
        return jiraIssueIds;
    }

    public void setJiraIssueIds(List<String> jiraIssueIds) {
        this.jiraIssueIds = jiraIssueIds;
    }
}
