import React, { Suspense } from 'react';
import "./i18n";
import ReactDOM from 'react-dom';
import App from './App';
import reportWebVitals from './reportWebVitals';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import ToDoApp from "./ToDoApp";
import EditItem from "./EditItem";
import Register from "./Register";
import Login from "./Login";
import Logout from "./Logout";
import AuthProvider from "./AuthProvider";



ReactDOM.render(
    <React.StrictMode>
        <Suspense fallback='Loading...'>
        <BrowserRouter>
            <AuthProvider>
            <Routes>
                <Route path="/" element={<Login/>}>
                   <Route path="/todoapp" element={<ToDoApp/>}/>
                    <Route path="/todoapp/:itemId" element={<EditItem/>}/>
                    <Route path="/todoapp/auth" element={<Register/>}/>
                    <Route path="/todoapp/auth/login" element={<Login/>}/>
                    <Route path="/todoapp/auth/logout" element={<Logout/>}/>
                    <Route path="/*" element={<ToDoApp/>}/>
                </Route>
            </Routes>
            </AuthProvider>
        </BrowserRouter>
        </Suspense>
    </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
