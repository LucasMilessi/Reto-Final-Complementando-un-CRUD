import React, {useContext, useRef, useState } from 'react';
import { useForm } from 'react-hook-form';
import {Store} from './useProviderCategory'; 


const HOST_API = "http://localhost:8080/api";

const FormCategory = () => {

    const { register } = useForm();
    const formRef = useRef(null);
    const { dispatch, state: { category } } = useContext(Store);
    const categoryitem = category.categoryitem;
    const [state, setState] = useState(categoryitem);

    
    const onAdd = (event) => {
      event.preventDefault();
  
      const request = {
        name: state.name,
        id: null,
        listTodo:[]
      };
  
  
      fetch(HOST_API + "/category", {
        method: "POST",
        body: JSON.stringify(request),
        headers: {
          'Content-Type': 'application/json'
        }
      })
        .then(response => response.json())
        .then((category) => {
          dispatch({ type: "add-category", categoryitem: category });
          setState({ name: "" });
          formRef.current.reset();
        });
    }
  
    const onEdit = (event) => {
      event.preventDefault();
        
      const request = {
        name: state.name,
        id: categoryitem.id,
        listTodo: []
      };
  
  
      fetch(HOST_API + "/category/"+ categoryitem.id, {
        method: "PUT",
        body: JSON.stringify(request),
        headers: {
          'Content-Type': 'application/json'
        }
      })
        .then(response => response.json())
        .then((category) => {
          dispatch({ type: "update-category", categoryitem: category });
          setState({ name: "" });
          formRef.current.reset();
        });
    }
  
    return (
      <div className="form-row align-items-center">
        <form className="form" ref={formRef}>
          <div className="col-auto">
            <center><input
              {...register('name', {
                required: true,
              })}
              placeholder="Ingrese una category"
              defaultValue={categoryitem.name}
              className="form-control mb-2 border border-dark text-warning w-50 p-1"
              onChange={(event) => {
                setState({ ...state, name: event.target.value })
              }} /></center>     
          </div>
          {categoryitem.id && <center><button className="btn btn-primary mb-2" onClick={onEdit}>Actualizar</button></center>}
          {!categoryitem.id && <div className="col-auto">  <center><button className="btn btn-warning m-2" onClick={onAdd}>Crear</button></center> </div>}
        </form>
      </div>
    )
  }
  export default FormCategory;