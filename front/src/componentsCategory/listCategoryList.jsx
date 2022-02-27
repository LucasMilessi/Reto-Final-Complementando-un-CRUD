import React, { useContext, useEffect } from 'react';
import FormListTodo from '../componentsListTodo/formListTodo';
import ListTodo from '../componentsListTodo/listTodo';
import UseProviderListTodo from '../componentsListTodo/useProviderListTodo'
import { Store } from './useProviderCategory';


const HOST_API = "http://localhost:8080/api";

const ListCategory = () => {


  const { dispatch, state: { category } } = useContext(Store);
  
  const currentList = category.listTodo;

  useEffect(() => {
    fetch(HOST_API + "/category")
      .then(response => response.json())
      .then((category) => {
        dispatch({ type: "all-listCategory", category })
      })
  }, [dispatch]);


  const onDelete = (id) => {
    fetch(HOST_API + "/category/" + id, {
      method: "DELETE"
    }).then((list) => {
      dispatch({ type: "delete-category", id })
    })
  };

  const printf = (objeto) => {
    return true;
  }

  const onEdit = (category) => {
    dispatch({ type: "edit-category", categoryitem: category })
    printf(category)
  };

  return (
  <div>
    <table className="table table-sm table-bordered border border-dark">
      <thead>
        <tr >
          <th className="text-center display-6">Id</th>
          <th className="text-center display-6">Categoria</th>
          <th className="text-center display-6">Lista de titulos de las categorias</th>
        </tr>
      </thead>
      <tbody>
        {
          currentList.map((category) => {
            return(
               <tr key={category.id}>
              <center><th><strong>{category.id}</strong></th></center>
              <td className="text-warning text-center m-2" ><strong>{category.name}
              <button className="btn btn-primary m-2" onClick={() => onEdit(category)}>Editar</button>
              <button className="btn btn-dark m-2" onClick={() => onDelete(category.id)}>Eliminar</button>
              </strong></td>
              {
                <td> 
                <UseProviderListTodo> 
                   <FormListTodo idlist={category.id} /> 
                    {  
                    <ListTodo idlist={category.id} />
                    }
                </UseProviderListTodo>
                </td>
              }           
            </tr>
            )
          })}
      </tbody>
    </table>
  </div>
  )
}
export default ListCategory;