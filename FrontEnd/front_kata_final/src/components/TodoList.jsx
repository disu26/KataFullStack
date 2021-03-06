import React, { useContext } from 'react'
import Form from './Form'
import List from './List'
import { Store } from '../store/Store'
import { HOST_API } from '../App'

/**
 * Componente que organiza la estructura principal de los todoList.
 * 
 * @returns 
 */
const TodoList = () => {
  const { dispatch, state: { todo,  todoList } } = useContext(Store);

  const currentList = todo.list;

  const listOfTodos = todoList.list;

  /**
   * Evento que se ejecuta al presionar el botón de eliminar.
   * 
   * @param {*} id 
   */
  const onDelete = (id) => {
    fetch(HOST_API + "/" + id + "/todoList", {
      method: "DELETE"
    }).then(() => {
      dispatch({ type: "delete-list", id })
    })
  }; 

  const todoListItems = listOfTodos.map((item) => {
    const filterList = currentList.filter((todoItem) => todoItem.groupListId  === item.id);
    return { ...item, filterList };
  });

  return (
      <div className='container mt-5 border border-secondary'>
        {todoListItems.map((todoList, index) => {
          return <div className='container mt-5 border border secondary' key={index}>
            <h3>{todoList.name}</h3>
            <button className='btn btn-danger' onClick={() => onDelete(todoList.id)}> Eliminar Lista </button>
            <Form list = {todoList}/>
            <List todos = {todoList.filterList}/>
          </div>
          })
        }
      </div>
    
  )
}

export default TodoList