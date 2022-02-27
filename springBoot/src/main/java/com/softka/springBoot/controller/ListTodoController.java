package com.softka.springBoot.controller;

import com.softka.springBoot.model.ListTodo;
import com.softka.springBoot.service.ListTodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Acá tenemos el ListTodoController, que contiene sus respectivos métodos GET, POST, DELETE y PUT.
 * @author lucas
 */

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ListTodoController {

    /**
     *Utilizamos el Autowired para poder utilizar los métodos de ListTodoService.
     */

    @Autowired
    ListTodoService listTodoService;

    /**
     * Aquí tenemos un GET, que nos devuelve una lista con todas las ListTodo.
     * @return
     */

    @GetMapping(value = "/todos")
    public ResponseEntity<Iterable<ListTodo>> listTodo(){
        return new ResponseEntity<>(listTodoService.listTodo(), HttpStatus.OK);
    }

    /**
     * Este POST, le pasamos un ID que va a referenciar con que categoría se va a referenciar y le pasamos un ListTodo y esto nos crea una ListTodo ya enlazada con una categoria.
     * @param listTodo
     * @param id
     * @return
     */

    @PostMapping(value = "/todo/{id}")
    public ResponseEntity<ListTodo> save(@RequestBody ListTodo listTodo,@PathVariable("id") Long id){
        try{
            return new ResponseEntity<>(listTodoService.addListTodo(listTodo,id), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Este DELETE, le pasamos un ID, para que nos borre la ListTodo especifico que queramos.
     * @param id
     * @return
     */

    @DeleteMapping(value = "/{id}/todo")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id){
        try{
            listTodoService.deleteListTodo(id);
            return new ResponseEntity<>("Se borro correctamente.", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Aquí este GET, nos muestra una ListTodo con el ID que le pasamos.
     * @param id
     * @return
     */

    @GetMapping(value = "/{id}/todo")
    public ResponseEntity<ListTodo> getTodo(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(listTodoService.getListTodo(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Aquí tenemos el POST, que nos va a editar una ListTodo.
     * @param listTodo
     * @param id
     * @return
     */

    @PutMapping(value = "/todo/{id}")
    public ResponseEntity<ListTodo> update(@RequestBody ListTodo listTodo,@PathVariable("id") Long id){
        try{
            if(listTodo.getId() != null){
                return new ResponseEntity<>(listTodoService.addListTodo(listTodo, id), HttpStatus.ACCEPTED);
            }
            throw new RuntimeException("El id: "+id+" no existe.");
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}

