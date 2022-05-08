
function reducer(state, action) {
    switch (action.type) {
      case 'update-item':
        const todoUpItem = state.todo;
        const listUpdateEdit = todoUpItem.list.map((item) => {
          if (item.id === action.item.id) {
            return action.item;
          }
          return item;
        });
        todoUpItem.list = listUpdateEdit;
        todoUpItem.item = {};
        return { ...state, todo: todoUpItem }
      case 'delete-item':
        const todoUpDelete = state.todo;
        const listUpdate = todoUpDelete.list.filter((item) => {
          return item.id !== action.id;
        });
        todoUpDelete.list = listUpdate;
        return { ...state, todo: todoUpDelete }
      case 'delete-list':
        const todoListUpDelete = state.todoList;
        const todoListUpdate = todoListUpDelete.list.filter((item) => {
          return item.id !== action.id; 
        });
        todoListUpDelete.list = todoListUpdate;
        return { ...state, todoList: todoListUpDelete }
      case 'update-list':
        const todoUpList = state.todo;
        todoUpList.list = action.list;
        return { ...state, todo: todoUpList }
      case 'update-todoList':
        const todoListUpList = state.todoList;
        todoListUpList.list = action.list;
        return { ...state, todoList: todoListUpList }
      case 'edit-item':
        const todoUpEdit = state.todo;
        todoUpEdit.item = action.item;
        return { ...state, todo: todoUpEdit }
      case 'add-item':
        const todoUp = state.todo.list;
        todoUp.push(action.item);
        return { ...state, todo: {list: todoUp, item: {}} } 
      case 'add-todoList':
        const todoListUp = state.list;
        return { ...state, todoList: {list: todoListUp, item: {}} }
      default:
        return state;
    }
  }

export default reducer;