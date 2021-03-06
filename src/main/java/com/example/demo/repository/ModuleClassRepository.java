package com.example.demo.repository;

import com.example.demo.persistence.IssueId;
import com.example.demo.persistence.ModuleClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleClassRepository extends CrudRepository<ModuleClass,Long> {
    List<ModuleClass> findByModule(final String module);

    List<ModuleClass> findByModuleAndClassPath(final String module,final String classpath);
    ModuleClass findByClassPath(final String classpath);

    List<ModuleClass> findByIssueListIn(final List<IssueId> jiraId);


}
