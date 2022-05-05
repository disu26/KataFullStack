
import React from 'react';
import TodoList from './components/TodoList';
import StoreProvider from './store/Store';

export const HOST_API = "http://localhost:9090/api";

function App() {
  return <StoreProvider>
    <div className='container mt-5'>
      <h3>To-Do List</h3>
      <button className='btn btn-primary'>Nueva Lista</button>
    </div>
    
    <TodoList />
  </StoreProvider>
}

export default App;