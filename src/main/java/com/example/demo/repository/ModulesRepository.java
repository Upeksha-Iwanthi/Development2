package com.example.demo.repository;

import com.example.demo.persistence.Modules;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModulesRepository extends CrudRepository<Modules,Long> {
    List<Modules> findByType(final String type);
    List<Modules> findBySvnURL(final String svnURL);
}
