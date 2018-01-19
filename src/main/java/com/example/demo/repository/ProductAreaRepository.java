package com.example.demo.repository;

import com.example.demo.persistance.ProductArea;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductAreaRepository extends CrudRepository<ProductArea,Long>{
    List<ProductArea> findByName(final String name);
}
