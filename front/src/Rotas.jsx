import React from 'react';
import  { Route, BrowserRouter, Routes } from "react-router-dom";

import Home from './screens/Home/Home';

export default function Rotas() {
    return(
        <BrowserRouter>
            <Routes>
                <Route path="/" element={ <Home /> } exact />
            </Routes>
        </BrowserRouter>
    );
}