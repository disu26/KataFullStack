import React, { useContext, useState, useEffect} from 'react'
import Form from './Form'
import List from './List'
import { Store } from '../store/Store'
import { HOST_API } from '../App'

const TodoList = () => {
  const { dispatch, state: { todoList } } = useContext(Store);

  const list = todoList.list;

  const onDelete = (id) => {
    fetch(HOST_API + "/" + id + "/todoList", {
      method: "DELETE"
    }).then((list) => {
      dispatch({ type: "delete-list", id })
    })
  }; 

  return (
      <div className='container mt-5 border border-secondary'>
        {list.map((todoList) => {
          return <div className='container mt-5 border border secondary' id={todoList.id}>
            <h3>{todoList.name}</h3>
            <button className='btn btn-danger' onClick={() => onDelete(todoList.id)}> Eliminar Lista </button>
            <Form list = {todoList}/>
            <List list = {todoList}/>
          </div>
        })
        }
        
      </div>
    
  )
}

export default TodoList