package com.softka.springBoot.controller;

import com.fasterxml.jackson.databind.util.ArrayIterator;
import com.softka.springBoot.model.Category;
import com.softka.springBoot.model.ListTodo;
import com.softka.springBoot.service.CategoryService;
import com.softka.springBoot.service.ListTodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Aqui tenemos el controllador de Category, este recibe las solicitudes del front.
 * @author lucas
 */

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CategoryController {

    /**
     * Aquí hacemos un Autowired que nos permite llamar los métodos de el CategoryService
     */
    @Autowired
    CategoryService categoryService;

    /**
     * Se hace un POST para agregar categorías.
     * @param category
     * @return
     */

    @PostMapping("/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        try {
            if(category.getName().isBlank()){
                throw new RuntimeException("El campo nombre esta vacio, debe ingresar un nombre.");
            }
            return new ResponseEntity<>(categoryService.addCategory(category), HttpStatus.CREATED);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     *Se hace un GET para que devuelva la lista de categorias que hay en la base de datos
     * @return
     */

    @GetMapping("/category")
    public ResponseEntity<List<Category>> allCategory(){
        try{
            List<Category> listCategory = (List<Category>) categoryService.allCategory();

            if(listCategory.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(listCategory, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     *  Aquí tenemos un GET que nos devuelve categorías a través de un ID que le pasamos nosotros
     * @param id
     * @return
     */

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> categoryById(@PathVariable("id") Long id){
        Optional<Category> optionalCategory = categoryService.getCategory(id);

        if (optionalCategory.isPresent()){
            return new ResponseEntity<>(optionalCategory.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Aquí tenemos un PUT que lo utilizamos para editar categorías, en este caso su nombre.
     * @param id
     * @param category
     * @return
     */

    @PutMapping("category/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id, @RequestBody Category category){
        try{
            if(category.getName().isBlank()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(categoryService.updateCategory(id, category), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Esto es un DELETE por ID, y lo utilizamos para eliminar una categoría y sus respectivas listas que están enlazadas con ella
     * @param id
     * @return
     */

    @DeleteMapping("/category/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id")Long id){
        try{
            categoryService.deleteCategoryById(id);
            return new ResponseEntity<>("Se elimino correctamente.", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
