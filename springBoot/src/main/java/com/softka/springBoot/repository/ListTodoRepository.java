package com.softka.springBoot.repository;

import com.softka.springBoot.model.Category;
import com.softka.springBoot.model.ListTodo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Esto es un repositorio con funciones CRUD.
 * @author lucas
 */

@Repository
public interface ListTodoRepository extends CrudRepository<ListTodo, Long> {

    /**
     * Aquí tenemos un método CRUD que nos devuelve una lista de ListTodo a través de categorías.
     * @param category
     * @return
     */
    List<ListTodo> findBylistCategory(Category category);
}

