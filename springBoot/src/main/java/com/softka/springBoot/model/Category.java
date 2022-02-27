package com.softka.springBoot.model;

import javax.persistence.*;
import java.util.List;

/**
 * @author lucas
 */

@Entity
public class Category {

    /**
     * Definimos el identificador de esta clase/tabla, y le pasamos qye sea autoincrementable
     */

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    /**
     *Hacemos un OneToMany con la clase ListTodo que es la que va a tener las listas de una categoria.
     */

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ListTodo> listTodo;

    /**
     * Creamos los get/set
     *
     */

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ListTodo> getListTodo() {
        return listTodo;
    }

    public void setListTodo(List<ListTodo> listTodo) {
        this.listTodo = listTodo;
    }
}
