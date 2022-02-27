package com.softka.springBoot.service;


import com.softka.springBoot.model.Category;
import com.softka.springBoot.model.ListTodo;
import com.softka.springBoot.repository.CategoryRepository;
import com.softka.springBoot.repository.ListTodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Aquí tenemos el service que tiene los métodos CRUD de ListTodo.
 * @author lucas
 */

@Service
public class ListTodoService {

    /**
     *Aquí tenemos los Autowired de los dos repositorios, porque vamos a utilizarlos para la creación de los métodos.
     */

    @Autowired
    ListTodoRepository listTodoRepository;

    @Autowired
    CategoryRepository categoryRepository;

    /**
     * Este método nos devuelve una lista con todos los ListTodo.
     * @return
     */

    public Iterable<ListTodo> listTodo(){
        return listTodoRepository.findAll();
    }

    /**
     * Este método nos crea ListTodo, y le pasamos un id para relacionarlo con la categoría deseada.
     * @param listTodo
     * @param id
     * @return
     */

    public ListTodo addListTodo(ListTodo listTodo, Long id) {
        Category category = categoryRepository.findById(id).orElseThrow();
        listTodo.setListCategory(category);
        return listTodoRepository.save(listTodo);
    }

    /**
     * Este método nos elimina una ListTodo con el id que le pasamos.
     * @param id
     */

    public void deleteListTodo(Long id){
        listTodoRepository.delete(getListTodo(id));
    }

    /**
     * Aquí este método nos devuelve, una ListTodo con el id que le pasamos.
     * @param id
     * @return
     */
    public ListTodo getListTodo(Long id){
        return listTodoRepository.findById(id).orElseThrow();
    }

}
