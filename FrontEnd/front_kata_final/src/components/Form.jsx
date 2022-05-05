import React, {useRef, useContext, useState} from 'react'
import { HOST_API } from '../App';
import { Store } from '../store/Store';

const Form = () => {
    const formRef = useRef(null);

    const { dispatch, state: { todo } } = useContext(Store);
    
    const item = todo.item;

    const [state, setState] = useState(item);

    const [errorMessage, seterrorMessage] = useState(false);
  
    const onAdd = (event) => {
      event.preventDefault();

      if(state.name !== undefined && state.name.length > 0 ){
        const request = {
          name: state.name,
          id: null,
          completed: false
        };
  
        fetch(HOST_API + "/todo", {
          method: "POST",
          body: JSON.stringify(request),
          headers: {
            'Content-Type': 'application/json'
          }
        })
        .then(response => response.json())
        .then((todo) => {
          dispatch({ type: "add-item", item: todo });
          setState({ name: "" });
          formRef.current.reset();
        });
        return;
      }
      seterrorMessage(true)
    }
  
    const onEdit = (event) => {
      event.preventDefault();
  
      if(state.name !== undefined && state.name.length > 0 ){
        const request = {
          name: state.name,
          id: item.id,
          isCompleted: item.isCompleted
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
          setState({ name: "" });
          formRef.current.reset();
        });
        return;
      }
      seterrorMessage(true);
      formRef.current.reset();
    }
  
    return (
        <div className='container mt-5'>
            <form ref={formRef}>
                <div className='form-group'>
                    <input
                        className='form-control'
                        type="text"
                        name="name"
                        placeholder="¿Qué piensas hacer hoy?"
                        defaultValue={item.name}
                        onChange={(event) => {
                          seterrorMessage(false);
                          setState({ ...state, name: event.target.value })
                        }
                    } />
                    <span className="text-danger text-small d-block mb-2">
                      {errorMessage ? 'No se puede crear un TO DO vacío' : '' }
                    </span>
                    <div className='mt-2'>
                        {item.id && <button className='btn btn-primary' onClick={onEdit}>Actualizar</button>}
                        {!item.id && <button className='btn btn-primary' onClick={onAdd}>Crear</button>}
                    </div>
                </div>
            </form>
        </div>
    )
}

export default Form