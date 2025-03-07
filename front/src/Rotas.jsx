import React from 'react';
import  { Route, BrowserRouter, Routes } from "react-router-dom";

import Home from './screens/Home/Home';
import Login from './screens/Login/Login';
import UserRegister from './screens/UserRegister/UserRegister';
import Profile from './screens/Profile/Profile';

export default function Rotas() {
    return(
        <BrowserRouter>
            <Routes>
                <Route path="/" element={ <Home /> } exact />
                <Route path="/login" element={ <Login /> } exact />
                <Route path="/register" element={ <UserRegister /> } exact />
                <Route path="/profile" element={ <Profile /> } exact />
            </Routes>
        </BrowserRouter>
    );
}