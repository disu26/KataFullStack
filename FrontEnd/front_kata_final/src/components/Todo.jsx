import React, { Fragment, useContext } from 'react'
import { HOST_API } from '../App';
import { Store } from '../store/Store';

/**
 * Componente que muestra cada todo.
 * 
 * @param {*} param0 
 * @returns 
 */
const Todo = ({item}) => {
    const { dispatch } = useContext(Store);

    const onDelete = (id) => {
        fetch(HOST_API + "/" + id + "/todo", {
          method: "DELETE",
          mode: 'cors',
          headers: {
            'Content-Type': 'application/json'
          },
          credentials: 'same-origin'
        }).then(() => {
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
          completed: event.target.checked,
          groupListId: todo.groupListId
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

      if(!item){
        return;
      }
  return (
      <Fragment>
            <tr key={item.id} style={item.completed ? decorationDone : {}}>
            <td>{item.id}</td>
            <td>{item.name}</td>
            <td><input type="checkbox" defaultChecked={item.completed} onChange={(event) => onChange(event, item)}></input></td>
            <td><button className='btn btn-danger' onClick={() => {onDelete(item.id)}}>Eliminar</button></td>
            <td>{!item.completed && <button className='btn btn-success' onClick={() => {onEdit(item)}}>Editar</button>}</td>
            </tr>
      </Fragment>
  )
}

export default Todo