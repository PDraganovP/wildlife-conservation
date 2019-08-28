import React from 'react';
import { Route, Switch, BrowserRouter as Router } from 'react-router-dom'
import './App.css';

import SpeciesForm from './components/SpeciesForm';
import Species from './components/Species';
import NavigationBar from './components/NavigationBar';
import NotFound from './components/NotFound';
import HomePage from './components/HomePage'

function App() {
  return (
    <React.Fragment>
      <Router>
        <NavigationBar />
        <Switch>
          <Route path='/' exact component={HomePage} />
          <Route path='/all-species' component={Species} />
          <Route path='/species-form' component={SpeciesForm} />
          <Route path={'/edit-species/:id'} component={SpeciesForm} />
          <Route component={NotFound} />
        </Switch>
      </Router>
    </React.Fragment>
  );
}

export default App;




