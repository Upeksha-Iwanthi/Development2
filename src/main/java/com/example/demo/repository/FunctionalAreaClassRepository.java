package com.example.demo.repository;

import com.example.demo.persistance.FunctionalAreaClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FunctionalAreaClassRepository extends CrudRepository<FunctionalAreaClass,Long> {
    List<FunctionalAreaClass> findByFunctionalAreaId(final long functionalAreaId);
}
