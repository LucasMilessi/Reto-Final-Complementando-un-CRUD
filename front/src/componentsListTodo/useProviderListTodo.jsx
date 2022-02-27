import React, { useReducer, createContext } from 'react';
import reducerListTodo from './reducerListTodo'


const Store = createContext()

const useProviderListTodo = ({ children }) => {

    const initialState = {

        listTodo: {
            list: [],
            item: {}
        }
    };

    const [state, dispatch] = useReducer(reducerListTodo, initialState);

    return (
        <Store.Provider
            value={{ state, dispatch }}>{children}
        </Store.Provider>
    )
}

export default useProviderListTodo;
export { Store };