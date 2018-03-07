package com.example.demo.service;

import com.example.demo.Data.IssueSearchResult;

import java.util.List;

public interface FindFunctionalAreaByClassService {
    List<IssueSearchResult> findFunctionalAreasByClass(String classPath);
}
