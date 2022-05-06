import React, { useContext, useEffect } from 'react'
import { Store } from '../store/Store';
import { HOST_API } from '../App';

const List = (listId) => {
    const { dispatch, state: { todo } } = useContext(Store);
    const currentList = todo.list;
  
    useEffect(() => {
      fetch(HOST_API + "/todos")
        .then(response => response.json())
        .then((list) => {
          dispatch({ type: "update-list", list })
        })
    }, [dispatch]);
  
  
    const onDelete = (id) => {
      fetch(HOST_API + "/" + id + "/todo", {
        method: "DELETE"
      }).then((list) => {
        dispatch({ type: "delete-item", id })
      })
    };
  
    const onEdit = (todo) => {
      dispatch({ type: "edit-item", item: todo })
    };
  
    const onChange = (event, todo) => {
      const request = {
        name: todo.name,
        id: todo.id,
        completed: event.target.checked
      };
      fetch(HOST_API + "/todo", {
        method: "PUT",
        body: JSON.stringify(request),
        headers: {
          'Content-Type': 'application/json'
        }
      })
        .then(response => response.json())
        .then((todo) => {
          dispatch({ type: "update-item", item: todo });
        });
    };
  
    const decorationDone = {
      textDecoration: 'line-through'
    };
    return <div className='container mt-5' id={listId.listId}>
      <table className='table'>
        <thead>
          <tr>
            <th scope='col'>ID</th>
            <th scope='col'>Tarea</th>
            <th scope='col'>¿Completado?</th>
          </tr>
        </thead>
        <tbody>
          {currentList.map((todo) => {
            return <tr key={todo.id} style={todo.completed ? decorationDone : {}}>
              <td>{todo.id}</td>
              <td>{todo.name}</td>
              <td><input type="checkbox" defaultChecked={todo.completed} onChange={(event) => onChange(event, todo)}></input></td>
              <td><button className='btn btn-danger' onClick={() => onDelete(todo.id)}>Eliminar</button></td>
              <td><button className='btn btn-success' onClick={() => onEdit(todo)}>Editar</button></td>
            </tr>
          })}
        </tbody>
      </table>
    </div>
}

export default List