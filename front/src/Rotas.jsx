import React from 'react';
import  { Route, BrowserRouter, Routes } from "react-router-dom";

import Home from './screens/Home/Home';
import HomeUser from './screens/HomeUser/HomeUser';
import Login from './screens/Login/Login';
import UserRegister from './screens/UserRegister/UserRegister';
import Profile from './screens/Profile/Profile';
import NovelRegister from './screens/NovelRegister/NovelRegister';
import Novel from './screens/Novel/Novel';
import Chapter from './screens/Chapter/Chapter';
import ChapterRegister from './screens/ChapterRegister/ChapterRegister';

export default function Rotas() {
    return(
        <BrowserRouter>
            <Routes>
                <Route path="/" element={ <Home /> } exact />
                <Route path="/login" element={ <Login /> } exact />
                <Route path="/register" element={ <UserRegister /> } exact />
                <Route path="/profile" element={ <Profile /> } exact />
                <Route path="/homeUser" element={ <HomeUser /> } exact />
                <Route path="/novelRegister" element={ <NovelRegister /> } exact />
                <Route path="/novel/:novelName" element={ <Novel /> } exact />
                <Route path="/novel/:novelName/chapter/:chapterNumber" element={ <Chapter /> } exact />
                <Route path="/chapterRegister" element={ <ChapterRegister /> } exact />
            </Routes>
        </BrowserRouter>
    );
}