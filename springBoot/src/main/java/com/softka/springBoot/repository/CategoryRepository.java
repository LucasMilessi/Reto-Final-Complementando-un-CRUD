package com.softka.springBoot.repository;

import com.softka.springBoot.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Esto es un repositorio con funciones CRUD.
 * @author lucas
 */

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
