package com.example.demo.persistence;

import java.util.List;

public interface ModulesDataService {
    List<Modules> getConfiguredBranchList(String type);

    List<IssueId> getIssueList(Modules devBranch);
}
