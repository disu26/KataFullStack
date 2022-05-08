import React, { useContext, useEffect } from 'react'
import { Store } from '../store/Store';
import { HOST_API } from '../App';
import Todo from './Todo';

const List = ({ todos }) => {

    const { dispatch } = useContext(Store);

    useEffect(() => {
      fetch(HOST_API + "/todo")
        .then(response => response.json())
        .then((list) => {
          dispatch({ type: "update-list", list })
        })
    }, [dispatch]);

    return <div className='container mt-5'>
      <table className='table'>
        <thead>
          <tr>
            <th scope='col'>ID</th>
            <th scope='col'>Tarea</th>
            <th scope='col'>¿Completado?</th>
          </tr>
        </thead>
        <tbody>
          {todos.map((todo, index) => (     
            <Todo key={index} item={todo} />
          ))}
        </tbody>
      </table>
    </div>
}

export default List