package com.example.demo.repository;

import com.example.demo.persistence.FunctionalArea;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FunctionalAreaRepository extends CrudRepository<FunctionalArea,Long> {
    List<FunctionalArea> findByName(final String name);
}
