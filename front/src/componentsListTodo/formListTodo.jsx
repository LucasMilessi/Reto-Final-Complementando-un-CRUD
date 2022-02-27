import React, { useContext, useRef, useState } from 'react';
import {Store} from './useProviderListTodo'; 


const HOST_API = "http://localhost:8080/api";

const FormListTodo = (idlistTodo) => {
  
    const formRef = useRef(null);
    const { dispatch, state: { listTodo } } = useContext(Store);
    const item = listTodo.item;
    const [state, setState] = useState(item);
    
  
    const onAdd = (event) => {
      event.preventDefault();
  
      const request = {
        name: state.name,
        id: null,
        completed: false,
      };
  
 
      fetch(HOST_API + "/todo/"+idlistTodo.idlist, {
        method: "POST",
        body: JSON.stringify(request),
        headers: {
          'Content-Type': 'application/json'
        }
      })
        .then(response => response.json())
        .then((listTodo) => {
          dispatch({ type: "add-ListTodo", item: listTodo });
          setState({ name: "" });
          formRef.current.reset();
        });
    }
  
    const onEdit = (event) => {
      event.preventDefault();
  
      const request = {
        name: state.name,
        id: item.id,
        isCompleted: item.isCompleted,
      };
  
  
      fetch(HOST_API + "/todo/"+idlistTodo.idlist, {
        method: "PUT",
        body: JSON.stringify(request),
        headers: {
          'Content-Type': 'application/json'
        }
      })
        .then(response => response.json())
        .then((listTodo) => {
          dispatch({ type: "update-ListTodo", item: listTodo });
          setState({ name: "" });
          formRef.current.reset();
        });
    }
  
    return <div className="form-row align-items-center">
    <form className="form" ref={formRef}>
      <center><input
        type="text"
        name="name"
        className="form-control mb-2 border border-dark text-warning w-50 p-1"
        placeholder="Titulo"
        defaultValue={item.name}
        onChange={(event) => {
          setState({ ...state, name: event.target.value })
        }}  ></input></center>
      {item.id && <center><button className="btn btn-primary mb-2" onClick={onEdit}>Actualizar</button></center>}
      {!item.id && <center><button  className="btn btn-warning mb-2" onClick={onAdd}>Crear</button></center>}
    </form>
    </div>
  }
  export default FormListTodo;