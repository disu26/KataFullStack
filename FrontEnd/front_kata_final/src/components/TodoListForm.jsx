import React, { useState, useEffect, useContext, Fragment, useRef } from 'react'
import TodoList from './TodoList';
import { HOST_API } from '../App'
import { Store } from '../store/Store';
import EmptyList from './EmptyList';

const TodoListForm = () => {
    const formRef = useRef(null);

    const { dispatch, state: { todoList } } = useContext(Store);

    const listTodoList = todoList.list;

    const [state, setState] = useState(listTodoList);

    const showTodoList = async() => {
        const response = await fetch(HOST_API + "/todoList")
        const todosList = await response.json();
        await dispatch({ type: "update-todoList", list: todosList })
        setState(todosList);
    }

    useEffect(() => {
        showTodoList();
    }, [dispatch]);

    const onAdd = (event) => {
        event.preventDefault();

        const request = {
            name: state.name,
            id: null
        };

        console.log(request)
    
        fetch(HOST_API + "/todoList", {
            method: "POST",
            body: JSON.stringify(request),
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => response.json())
        .then((todoList) => {
            dispatch({ type: "add-todoList",  todoList});
            setState({ name: "" });
            console.log(listTodoList)
            formRef.current.reset();
        });
        
    }

  return (
    <Fragment>
        <div className='container mt-5'>
        <h3>To-Do List</h3>
            <form ref={formRef}>
                <input className='form-control'
                    type="text"
                    name="name"
                    placeholder="Lista de TO-DO"
                    onChange={(event) => {
                        setState({ ...state, name: event.target.value })
                      }
                    } 
                />
                <button className='btn btn-primary mt-2' onClick={onAdd}>Nueva Lista</button>
            </form>
        </div>
        { listTodoList.length > 0 ? <TodoList /> : <EmptyList /> }
    </Fragment>
  )
}

export default TodoListForm