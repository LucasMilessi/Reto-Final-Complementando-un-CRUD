function reducerCategory(state, action) {
    switch (action.type) {

        case "add-category":
            const categoryUp = state.category.listTodo;
            categoryUp.push(action.categoryitem);
            return { ...state, category: { listTodo: categoryUp, categoryitem: {} } }

        case "update-category":
            const categoryUpItem = state.category;
            const listUpdateEdit = categoryUpItem.listTodo.map((categoryitem) => {
                if (categoryitem.id === action.categoryitem.id) {
                    return action.categoryitem;
                }
                return categoryitem;
            });
            categoryUpItem.listTodo = listUpdateEdit;
            categoryUpItem.categoryitem = {};
            return { ...state, listTodo: categoryUpItem }

        case "delete-category":
            const categoryUpDelete = state.category;
            const todolistUpdate = categoryUpDelete.listTodo.filter((categoryitem) => {
                return categoryitem.id !== action.id;
            });
            categoryUpDelete.listTodo = todolistUpdate;
            return { ...state, todoList: categoryUpDelete }

        case "all-listCategory":
            const categoryUpList = state.category;
            categoryUpList.listTodo = action.category;

            return { ...state, category: categoryUpList }
            
        case "edit-category":
            const categoryUpEdit = state.category;
            categoryUpEdit.categoryitem = action.categoryitem;
            return { ...state, category: categoryUpEdit }
        
        default:
            return state;
    }
}
export default reducerCategory;