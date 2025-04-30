import React from 'react';
import  { Route, BrowserRouter, Routes } from "react-router-dom";

import Home from './screens/Home/Home.jsx';
import HomeUser from './screens/HomeUser/HomeUser.jsx';
import Login from './screens/Login/Login.jsx';
import UserRegister from './screens/UserRegister/UserRegister';
import Profile from './screens/Profile/Profile.jsx';
import NovelRegister from './screens/NovelRegister/NovelRegister';
import Novel from './screens/Novel/Novel.jsx';
import Chapter from './screens/Chapter/Chapter.jsx';
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
                <Route path="/novel/:novelId" element={ <Novel /> } exact />
                <Route path="/novel/:novelName/chapter/:chapterNumber" element={ <Chapter /> } exact />
                <Route path="/chapterRegister/:novelId" element={ <ChapterRegister /> } exact />
            </Routes>
        </BrowserRouter>
    );
}