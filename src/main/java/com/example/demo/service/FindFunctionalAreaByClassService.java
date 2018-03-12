package com.example.demo.service;

import com.example.demo.Data.IssueSearchResultRow;

import java.util.List;

public interface FindFunctionalAreaByClassService {
    List<IssueSearchResultRow> findFunctionalAreasByClass(String classPath);
}
