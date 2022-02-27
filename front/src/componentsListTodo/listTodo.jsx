import React, { useContext, useEffect } from 'react';
import { Store } from './useProviderListTodo';


const HOST_API = "http://localhost:8080/api";

const ListTodo = (idlist) => {

  const { dispatch, state: { listTodo } } = useContext(Store);
  const currentList = listTodo.list;


  useEffect(() => {
    fetch(HOST_API + "/todos")
      .then(response => response.json())
      .then((list) => {
        dispatch({ type: "all-ListTodo", list })
      })
  }, [dispatch]);


  const onDelete = (id) => {
    fetch(HOST_API + "/" + id + "/todo", {
      method: "DELETE"
    }).then((list) => {
      dispatch({ type: "delete-ListTodo", id })
    })
  };

  const onEdit = (listTodo) => {
    dispatch({ type: "edit-ListTodo", item: listTodo })
  };

  const onChange = (event, listTodo) => {
    const request = {
      name: listTodo.name,
      id: listTodo.id,
      completed: event.target.checked,
    };

    fetch(HOST_API + "/todo/" + idlist.idlist, {
      method: "PUT",
      body: JSON.stringify(request),
      headers: {
        'Content-Type': 'application/json'
      }
    })
      .then(response => response.json())
      .then((listTodo) => {
        dispatch({ type: "update-ListTodo", item: listTodo });
      });
  };

  const decorationDone = {
    textDecoration: 'line-through'
  };

  return (
    <div className="container-fluid">
      <table className="table table-striped table-bordered border border-warning">
        <thead>
          <tr>
            <th className="text-center">Id</th>
            <th className="text-center">Nombre</th>
            <th className="text-center">Completado</th>
            <th className="text-center">Editar/Eliminar</th>
          </tr>
        </thead>
        <tbody>
          {currentList.map(
            (listTodo) => {
              return listTodo.listCategory.id === idlist.idlist && <tr key={listTodo.id} style={listTodo.completed ? decorationDone : {}}>
                <td><p className="text-success text-center m-2"><strong>{listTodo.id}</strong></p></td>
                <td className="text-success text-center m-2"><strong>{listTodo.name}</strong></td>
                <div class="form-check form-switch">
                  <center><td><input className="form-check-input m-2" type="checkbox" for="flexSwitchCheckDefault" defaultChecked={listTodo.completed} onChange={(event) => onChange(event, listTodo)}></input></td></center>
                </div>
                <td><center><button className="btn btn-primary m-2" onClick={() => onEdit(listTodo)} disabled={listTodo.completed}>Editar</button></center>
                  <center><button className="btn btn-dark" onClick={() => onDelete(listTodo.id)}> Eliminar </button></center>
                </td>
              </tr>
            })}
        </tbody>
      </table>
    </div>
  )
}
export default ListTodo;