package com.example.demo.repository;

import com.example.demo.persistence.IssueId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueIdRepository extends CrudRepository<IssueId,Long>{

    List<IssueId> findByIssueId(final String issueId);

}
