import React from "react";
import "./App.css";
import Header from "./components/layouts/Header.jsx";
import { Route, Switch } from "react-router-dom";
import MessageDashboard from "./components/message/MessageDashboard";
import UpdateMessage from "./components/message/UpdateMessage";
import AddMessage from "./components/message/AddMessage.jsx";
import SearchMessage from "./components/message/SearchMessage.jsx";

function App() {
  return (
    <div className="App">
      <Header />
      <Switch>
        <Route exact path="/messages" component={MessageDashboard} />
        <Route exact path="/messages/:id" component={UpdateMessage} />
        <Route exact path="/newMessageForm" component={AddMessage} />
        <Route exact path="/searchMessageForm" component={SearchMessage} />
      </Switch>
    </div>
  );
}

export default App;
