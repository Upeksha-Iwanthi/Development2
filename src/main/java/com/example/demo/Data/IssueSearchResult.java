package com.example.demo.Data;

import java.util.List;

public class IssueSearchResult {

    private String message;
    private List<IssueSearchResultRow> issueSearchResultList;

    public List<IssueSearchResultRow> getIssueSearchResultList() {
        return issueSearchResultList;
    }

    public void setIssueSearchResultList(List<IssueSearchResultRow> issueSearchResultList) {
        this.issueSearchResultList = issueSearchResultList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
