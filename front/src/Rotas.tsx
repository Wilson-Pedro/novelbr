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
import CommentArea from './component/CommentArea/CommentArea';

const Rotas: React.FC = () => {
    return(
        <BrowserRouter>
            <Routes>
                <Route path="/" element={ <Home /> } />
                <Route path="/login" element={ <Login /> } />
                <Route path="/register" element={ <UserRegister /> } />
                <Route path="/profile/:username" element={ <Profile /> } />
                <Route path="/homeUser" element={ <HomeUser /> } />
                <Route path="/novelRegister" element={ <NovelRegister /> } />
                <Route path="/novel/:novelId" element={ <Novel /> } />
                <Route path="/novel/:novelName/chapter/:chapterNumber" element={ <Chapter /> } />
                <Route path="/chapterRegister/:novelId" element={ <ChapterRegister /> } />
                <Route path="/comments/test" element={ <CommentArea/> } />
            </Routes>
        </BrowserRouter>
    );
}

export default Rotas;