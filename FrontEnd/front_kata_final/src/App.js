
import React from 'react';
import Form from './components/Form';
import List from './components/List';
import StoreProvider from './store/Store';

export const HOST_API = "http://localhost:9090/api";

function App() {
  return <StoreProvider>
    <div className='container mt-5'><h3>To-Do List</h3></div>
    <Form />
    <List />
  </StoreProvider>
}

export default App;