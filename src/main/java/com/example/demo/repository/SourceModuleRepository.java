package com.example.demo.repository;

import com.example.demo.persistance.SourceModule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SourceModuleRepository extends CrudRepository<SourceModule,Long> {
    List<SourceModule> findBySvnURL(final String userName);
}
