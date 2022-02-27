import React, { useReducer, createContext } from 'react';
import reducerCategory from './reducerCategory'


const Store = createContext()

const useProviderCategory = ({ children }) => {

    const initialState = {
        category: {
            listTodo: [],
            categoryitem: {}
        }
    };

    const [state, dispatch] = useReducer(reducerCategory, initialState);

    return (<Store.Provider value={{ state, dispatch }}>{children}</Store.Provider>);
}

export default useProviderCategory;
export { Store };