import React, { useContext } from 'react'
import Form from './Form'
import List from './List'
import { Store } from '../store/Store'
import { HOST_API } from '../App'

const TodoList = ({ listTodoList }) => {
  const { dispatch, state: { todo,  todoList } } = useContext(Store);

  const currentList = todo.list;

  const listOfTodos = todoList.list;

  const onDelete = (id) => {
    fetch(HOST_API + "/" + id + "/todoList", {
      method: "DELETE"
    }).then(() => {
      dispatch({ type: "delete-list", id })
    })
  }; 

  const todoListItems = listOfTodos.map((item) => {
    const filterList = currentList.filter((todoItem) => todoItem.groupListId  === item.id);
    return { ...currentList, filterList };
  });

  return (
      <div className='container mt-5 border border-secondary'>
        {todoListItems.map((todoList) => {
          return <div className='container mt-5 border border secondary' id={todoList.id}>
            <h3>{listTodoList.list.name}</h3>
            <button className='btn btn-danger' onClick={() => onDelete(todoList.id)}> Eliminar Lista </button>
            <Form list = {listTodoList}/>
            <List todos = {todoList.filterList}/>
          </div>
          })
        }
      </div>
    
  )
}

export default TodoList