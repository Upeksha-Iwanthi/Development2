package com.example.demo.repository;

import com.example.demo.persistence.ModuleClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleClassRepository extends CrudRepository<ModuleClass,Long> {
    List<ModuleClass> findByModule(final String module);
}
