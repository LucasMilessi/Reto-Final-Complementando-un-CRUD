import React from 'react';
import StoreProviderList from './componentsCategory/useProviderCategory'
import FormCategory from './componentsCategory/formCategory'
import ListTodoList from './componentsCategory/listCategoryList'

function App() {
  return (
    <StoreProviderList>
      <center>
        <h1 className="display-1"><strong>CATEGORIAS</strong></h1>
      </center>
      <div className="container-fluid m-2">
        <FormCategory />
      </div>
      <div>
      <ListTodoList />
      </div>
    </StoreProviderList>
  );
  
}

export default App;