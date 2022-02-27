function reducerListTodo(state, action) {
    switch (action.type) {

        case "all-ListTodo":
            const listTodoUpList = state.listTodo;
            listTodoUpList.list = action.list;
            return { ...state, listTodo: listTodoUpList }

        case "update-ListTodo":
            const listTodoUpItem = state.listTodo;
            const listUpdateEdit = listTodoUpItem.list.map((item) => {
                if (item.id === action.item.id) {
                    return action.item;
                }
                return item;
            });
            listTodoUpItem.list = listUpdateEdit;
            listTodoUpItem.item = {};
            return { ...state, listTodo: listTodoUpItem }

        case "delete-ListTodo":
            const listTodoUpDelete = state.listTodo;
            const listTodoUpdate = listTodoUpDelete.list.filter((item) => {
                return item.id !== action.id;
            });
            listTodoUpDelete.list = listTodoUpdate;
            return { ...state, listTodo: listTodoUpDelete }
        
        case "edit-ListTodo":
            const listTodoUpEdit = state.listTodo;
            listTodoUpEdit.item = action.item;
            return { ...state, listTodo: listTodoUpEdit }

        case "add-ListTodo":
            const listTodoUp = state.listTodo.list;
            listTodoUp.push(action.item);
            return { ...state, listTodo: { list: listTodoUp, item: {} } }

        default:
            return state;
    }
}
export default reducerListTodo;