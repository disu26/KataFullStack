import React from 'react';
import TodoListForm from './components/TodoListForm';
import StoreProvider from './store/Store';

export const HOST_API = "http://localhost:9090/api";

function App() {
  return <StoreProvider>
    <TodoListForm />
  </StoreProvider>
}

export default App;