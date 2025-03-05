import React from 'react';
import  { Route, BrowserRouter, Routes } from "react-router-dom";

import Home from './screens/Home/Home';
import Login from './screens/Login/Login';

export default function Rotas() {
    return(
        <BrowserRouter>
            <Routes>
                <Route path="/" element={ <Home /> } exact />
                <Route path="/login" element={ <Login /> } exact />
            </Routes>
        </BrowserRouter>
    );
}