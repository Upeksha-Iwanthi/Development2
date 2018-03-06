package com.example.demo.repository;

import com.example.demo.persistence.IssueId;
import com.example.demo.persistence.ModuleClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueIdRepository extends CrudRepository<IssueId,Long>{

    List<IssueId> findByIssueId(final String issueId);
    List<IssueId> findByIssueIdAndModuleClass(final String issueId, final ModuleClass moduleClass);

}
