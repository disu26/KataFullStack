import React from 'react'
import Form from './Form'
import List from './List'

const TodoList = () => {
  return (
      <div className='container mt-5 border border-secondary'>
        <h3>Todo 1</h3>
        <button className='btn btn-danger'> Eliminar Lista </button>
        <Form />
        <List />
      </div>
    
  )
}

export default TodoList