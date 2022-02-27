package com.softka.springBoot.service;

import com.softka.springBoot.model.Category;
import com.softka.springBoot.model.ListTodo;
import com.softka.springBoot.repository.CategoryRepository;
import com.softka.springBoot.repository.ListTodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Aquí tenemos un servicio, que tiene todos los métodos que llama el CategoryController.
 * @author lucas
 */

@Service
public class CategoryService {

    /**
     *Aquí tenemos los Autowired de los dos repositorios, porque vamos a utilizarlos para la creación de los métodos.
     */

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ListTodoRepository listTodoRepository;

    /**
     * Aquí tenemos el método, que nos permite agregar categorías.
     * @param category
     * @return
     */

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    /**
     * Este método nos devuelve la lista de todas las categorías que están en la base de datos.
     * @return
     */

    public Iterable<Category> allCategory(){
        return categoryRepository.findAll();
    }

    /**
     * Este método nos devuelve la categoría con el ID que le pasemos.
     * @param id
     * @return
     */

    public Optional<Category> getCategory(Long id){
        return categoryRepository.findById(id);

    }

    /**
     *Este método nos permite editar una categoría que esté en la base de datos.
     * @param id
     * @param category
     * @return
     */

    public Category updateCategory(Long id, Category category){
        Category listTodoSave = this.getCategory(id).orElseThrow();

        listTodoSave.setName(category.getName());

        return categoryRepository.save(category);
    }

    /**
     * Este método nos permite eliminar las categorías y sus respectivas ListTodo que están conectadas.
     * @param id
     */

    public void deleteCategoryById(Long id){
        Category category = categoryRepository.findById(id).orElseThrow();
        //Obtenemos la lista de ListTodo que están vinculadas al id de categoría que le pasamos.
        Iterable<ListTodo> listTodos = listTodoRepository.findBylistCategory(category);

        //Este foreach, nos recorre la lista anterior y va eliminando cada una de ellas.
        for (ListTodo listTodo: listTodos) {
            listTodoRepository.deleteById(listTodo.getId());
        }
        //Por último, una vez que todas las ListTodo que están vinculadas con la categoría, podemos eliminar la categoría deseada.
        categoryRepository.deleteById(id);
        }
}
