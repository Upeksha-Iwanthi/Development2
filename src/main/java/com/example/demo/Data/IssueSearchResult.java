package com.example.demo.Data;

import com.example.demo.persistence.FunctionalArea;

import java.util.List;

public class IssueSearchResult {
    private String classPath;
    private String module;
    private String functionalArea;
    private String productArea;
    private double percentage;
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

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public List<String> getJiraIssueIds() {
        return jiraIssueIds;
    }

    public void setJiraIssueIds(List<String> jiraIssueIds) {
        this.jiraIssueIds = jiraIssueIds;
    }

    public String getProductArea() {
        return productArea;
    }

    public void setProductArea(String productArea) {
        this.productArea = productArea;
    }
}
