package com.example.demo.repository;

import com.example.demo.persistence.FunctionalArea;
import com.example.demo.persistence.FunctionalAreaClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FunctionalAreaClassRepository extends CrudRepository<FunctionalAreaClass,Long> {
    List<FunctionalAreaClass> findByJiraIssueIdAndFunctionaArea(final String jiraIssueID, FunctionalArea functionalArea);
}
