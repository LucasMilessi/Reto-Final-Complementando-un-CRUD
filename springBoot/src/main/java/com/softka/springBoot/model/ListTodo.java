package com.softka.springBoot.model;

import javax.persistence.*;

/**
 * @author lucas
 */

@Entity
@Table(name = "todo")
public class ListTodo {

    /**
     * Se crea el identificador de tipo Long de esta clase/tabla.
     */

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private boolean isCompleted;

    /**
     * Aquí se hace el ManyToOne y el la union a través del Join con la clase/tabla Category.
     */

    @ManyToOne
    @JoinColumn(name = "listCategory")
    private Category listCategory;

    /**
     * Sus respectivos get/set
     * @return
     */

    public Category getListCategory() {
        return listCategory;
    }

    public void setListCategory(Category listTodo) {
        this.listCategory = listTodo;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}

